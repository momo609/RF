
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 
public class Id3 {
    private ArrayList<String> attribute = new ArrayList<String>(); // 存储属性的名称
    private ArrayList<ArrayList<String>> attributevalue = new ArrayList<ArrayList<String>>(); // 存储每个属性的取值
    private ArrayList<String[]> data = new ArrayList<String[]>();; // 原始数据
    int decatt=4; // 决策变量在属性集中的索引
    public static final String patternString = "@attribute(.*)[{](.*?)[}]";
 
 
    public static void main(String[] args) {
        Id3 inst = new Id3();
        inst.readtxt(new File("/home/orisun/test/weather.nominal.arff"));
//        inst.setDec("play");
        LinkedList<Integer> ll=new LinkedList<Integer>();
        for(int i=0;i<inst.attribute.size();i++){
            if(i!=inst.decatt)
                ll.add(i);
        }
        ArrayList<Integer> al=new ArrayList<Integer>();
        for(int i=0;i<inst.data.size();i++){
            al.add(i);
        }
       Tree tree=inst.buildDT("DecisionTree", "null", al, ll);
       System.out.println("22");
        return;
    }
 
    //读取arff文件，给attribute、attributevalue、data赋值
    public void readtxt(File file) {
        try {
            FileReader fr = new FileReader("E:/attribute.txt");
            BufferedReader br = new BufferedReader(fr);
            FileReader fr2 = new FileReader("E:/datasets.txt");
            BufferedReader br2 = new BufferedReader(fr2);
            String line;
            String[] s;
            while ((line = br.readLine()) != null) {
            	    s=line.split("@");
                    attribute.add(s[0]);
                    ArrayList<String> al = new ArrayList<String>(s.length-1);
                    for (int i=1;i<s.length;i++) {
                        al.add(s[i]);
                    }
                    attributevalue.add(al);
            }       
             while ((line = br2.readLine()) != null) {
                        String[] row = line.split(",");
                        data.add(row);
             }
            br.close();
            br2.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
 
    //设置决策变量
    public void setDec(int n) {
        if (n < 0 || n >= attribute.size()) {
            System.err.println("决策变量指定错误。");
            System.exit(2);
        }
        decatt = n;
    }
    public void setDec(String name) {
        int n = attribute.indexOf(name);
        setDec(n);
    }
 
    //给一个样本（数组中是各种情况的计数），计算它的熵
    public double getEntropy(int[] arr) {
        double entropy = 0.0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            entropy -= arr[i] * Math.log(arr[i]+Double.MIN_VALUE)/Math.log(2);
            sum += arr[i];
        }
        entropy += sum * Math.log(sum+Double.MIN_VALUE)/Math.log(2);
        System.out.println("11 "+sum * Math.log(sum+Double.MIN_VALUE)/Math.log(2));
        entropy /= sum;
        System.out.println("entropy"+entropy);
        return entropy;
    }
 
    //给一个样本数组及样本的算术和，计算它的熵
    public double getEntropy(int[] arr, int sum) {
        double entropy = 0.0;
        for (int i = 0; i < arr.length; i++) {
            entropy -= arr[i] * Math.log(arr[i]+Double.MIN_VALUE)/Math.log(2);
        }
        entropy += sum * Math.log(sum+Double.MIN_VALUE)/Math.log(2);
        entropy /= sum;
        return entropy;
    }
 
    public boolean infoPure(ArrayList<Integer> subset) {
        String value = data.get(subset.get(0))[decatt];
        for (int i = 1; i < subset.size(); i++) {
            String next=data.get(subset.get(i))[decatt];
            //equals表示对象内容相同，==表示两个对象指向的是同一片内存
            if (!value.equals(next))
                return false;
        }
        return true;
    }
 
    // 给定原始数据的子集(subset中存储行号),当以第index个属性为节点时计算它的信息熵
    public double calNodeEntropy(ArrayList<Integer> subset, int index) {
    	int totalcount[]=countyes(subset);
//    	System.out.println("totalcount[0]"+totalcount[0]);
//    	System.out.println("totalcount[1]"+totalcount[1]);
    	double entd;
    	if(totalcount[1]==0&&totalcount[0]==0)
    	{
    		entd=0;
    	}
    	else if(totalcount[1]==0)
    	{
    		entd=-((double)totalcount[0]/data.size()*Math.log((double)totalcount[0]/data.size())/Math.log(2));
    	}
    	else if(totalcount[0]==0)
    	{
    		entd=-((double)totalcount[1]/data.size()*Math.log((double)totalcount[1]/data.size())/Math.log(2));
    	}
    	else
    	{
    		entd=-((double)totalcount[0]/data.size()*Math.log((double)totalcount[0]/data.size())/Math.log(2)+(double)totalcount[1]/data.size()*Math.log((double)totalcount[1]/data.size())/Math.log(2));
    	}
        int sum = subset.size();
       // System.out.println("decatt"+decatt);
        double entropy = 0.0;
        int[][] info = new int[attributevalue.get(index).size()][];
        for (int i = 0; i < info.length; i++)
            info[i] = new int[attributevalue.get(decatt).size()];
        int[] count = new int[attributevalue.get(index).size()];
        int[] county = new int[attributevalue.get(index).size()];
        int[] countn = new int[attributevalue.get(index).size()];
        double[] value=new double[attributevalue.get(index).size()];
       // System.out.println(index);
        for (int i = 0; i < sum; i++) {
            int n = subset.get(i);
            String nodevalue = data.get(n)[index];
            //System.out.println(data.get(n)[index]);
            int nodeind = attributevalue.get(index).indexOf(nodevalue);
            if(data.get(n)[4].equals("yes"))
            	county[nodeind]++;
            else
            	countn[nodeind]++;
            count[nodeind]++; //计算每个属性取值的个数
//            String decvalue = data.get(n)[decatt];
//            int decind = attributevalue.get(decatt).indexOf(decvalue);
//            info[nodeind][decind]++;
        }
//        System.out.println("countn"+countn[0]);
//        System.out.println("county"+county[0]);
        for(int i=0;i<value.length;i++)
        {
        	double a=(double)countn[i]/(double)sum;
        	double b=+(double)county[i]/(double)sum;
//        	System.out.println(i+" "+a);
//        	System.out.println(i+" "+b);
        	if(a==0&&b==0)
        	{
        		value[i]=0;
        		
        	}
        	else if(b==0)
        	{
        		value[i]=a*Math.log(a);
        	}
        	else if(a==0)
        	{
        		value[i]=b*Math.log(b);
        	}
        	else
        	{
        	    value[i]=-(a*Math.log(a)/Math.log(2)+b*Math.log(b)/Math.log(2));
        	}
//        	System.out.println(i+" "+countn[i]);
//        	System.out.println(i+" "+count[i]);
//        	System.out.println(i+" "+value[i]);
        }
        for (int i = 0; i < value.length; i++) {
            entropy +=value[i];
        }
//        System.out.println("entd "+entd);
//        System.out.println("entropy"+entropy);
        entropy=entd-entropy;
        return entropy;
    }
 
    // 构建决策树
    public Tree buildDT(String name, String value, ArrayList<Integer> subset,
            LinkedList<Integer> selatt) {
//        Element ele = null;
//        @SuppressWarnings("unchecked")
//        List<Element> list = root.selectNodes("//"+name);
//        Iterator<Element> iter=list.iterator();
//        while(iter.hasNext()){
//            ele=iter.next();
//            if(ele.attributeValue("value").equals(value))
//                break;
//        }
//        if (infoPure(subset)) {
//            ele.setText(data.get(subset.get(0))[decatt]);
//            return;
//        }
    	 System.out.println("selatt.size() "+selatt.size());
    	 if(selatt.size()==1)
    	 {
    		 Tree node=null;
    		 int sum = subset.size();
    		 int max=0;
    		 int index = 0;
    		 int[] count = new int[attributevalue.get(selatt.get(0)).size()];
    		 for (int i = 0; i < sum; i++) {
    	            int n = subset.get(i);
    	            String nodevalue = data.get(n)[selatt.get(0)];
    	            //System.out.println(data.get(n)[index]);
    	            int nodeind = attributevalue.get(selatt.get(0)).indexOf(nodevalue);
    	            count[nodeind]++; //计算每个属性取值的个数
    	       }
    		 for(int i=0;i<count.length;i++)
    		 {
    			 if(count[i]>max)
    			 {
    				 max=count[i];
    				 index=i;
    			 }
    				 
    		 }
    		 node=new Tree(attributevalue.get(selatt.get(0)).get(index));
    		 System.out.println(node);
    		 return node;
    	 }
        int minIndex = -1;
        double minEntropy = Double.MAX_VALUE;
        for (int i = 0; i < selatt.size(); i++) {
        	if (i == decatt)
                continue;
            double entropy = calNodeEntropy(subset, selatt.get(i));
            //System.out.println("entropy "+entropy);
            if (entropy < minEntropy) {
            	//System.out.println("111");
                minIndex = selatt.get(i);
                minEntropy = entropy;
            }
        }
//        System.out.println("minIndex"+minIndex);
        String nodeName = attribute.get(minIndex);
//        System.out.println("nodeName "+nodeName);
        Tree node = new Tree(nodeName);
       
          
        selatt.remove(new Integer(minIndex));
        ArrayList<String> attvalues = attributevalue.get(minIndex);
        for (String val : attvalues) {
           // ele.addElement(nodeName).addAttribute("value", val);
            ArrayList<Integer> al = new ArrayList<Integer>();
            for (int i = 0; i < subset.size(); i++) {
                if (data.get(subset.get(i))[minIndex].equals(val)) {
//                	System.out.println(i);
                    al.add(subset.get(i));
                }
            }
          Tree child=buildDT(nodeName, val, al, selatt);
          node.setChild(nodeName, child);
        }
        return node;
    }
    public int[] countyes(ArrayList<Integer> subset){
    	int count[]=new int[2];
    	int sum = subset.size();
    	for (int i = 0; i < sum; i++) {
            int n = subset.get(i);
            if(data.get(n)[4].equals("yes"))
            	count[0]++;
            else
            	count[1]++;
        }
    	return count;
    	
    }
    static class Tree {
    	 
        private String attribute;
 
        private Map<Object, Object> children = new HashMap<Object, Object>();
 
        public Tree(String attribute) {
            this.attribute = attribute;
        }
 
        public String getAttribute() {
            return attribute;
        }
 
        public Object getChild(Object attrValue) {
            return children.get(attrValue);
        }
 
        public void setChild(Object attrValue, Object child) {
            children.put(attrValue, child);
        }
 
        public Set<Object> getAttributeValues() {
            return children.keySet();
        }
 
    }
 
    // 把xml写入文件
//    public void writeXML(String filename) {
//        try {
//            File file = new File(filename);
//            if (!file.exists())
//                file.createNewFile();
//            FileWriter fw = new FileWriter(file);
//            OutputFormat format = OutputFormat.createPrettyPrint(); // 美化格式
//            XMLWriter output = new XMLWriter(fw, format);
//            output.write(xmldoc);
//            output.close();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
}