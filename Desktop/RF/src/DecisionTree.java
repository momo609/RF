import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DecisionTree {
    private Integer attrSelMode; // ��ѷ�������ѡ��ģʽ��1��ʾ����Ϣ���������2��ʾ����Ϣ�����ʶ�������δʵ��2

    public DecisionTree() {
        this.attrSelMode = 1;
    }

    public DecisionTree(int attrSelMode) {
        this.attrSelMode = attrSelMode;
    }

    public void setAttrSelMode(Integer attrSelMode) {
        this.attrSelMode = attrSelMode;
    }

    /**
     * ��ȡָ�����ݼ��е���������
     * 
     * @param datas
     *            ָ�������ݼ�
     * @return ����������map
     */
    public static Map<String, Integer> classOfDatas(
            ArrayList<ArrayList<String>> datas,int index) {
        Map<String, Integer> classes = new HashMap<String, Integer>();
        String c = "";
        ArrayList<String> tuple = null;
        for (int i = 0; i < datas.size(); i++) {
            tuple = datas.get(i);
            c = tuple.get(index);
            if (classes.containsKey(c)) {
                classes.put(c, classes.get(c) + 1);
            } else {
                classes.put(c, 1);
            }
        }
        return classes;
    }

    /**
     * ��ȡ���������������������������
     * 
     * @param classes
     *            ��ļ�ֵ����
     * @return �����������
     */
    public static String maxClass(Map<String, Integer> classes) {
        String maxC = "";
        int max = -1;
        Iterator iter = classes.entrySet().iterator();
        for (int i = 0; iter.hasNext(); i++) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (val > max) {
                max = val;
                maxC = key;
            }
        }
        return maxC;
    }

    /**
     * ���������
     * 
     * @param datas
     *            ѵ��Ԫ�鼯��
     * @param attrList
     *            ��ѡ���Լ���
     * @return �����������
     */
    public static TreeNode buildTree(ArrayList<ArrayList<String>> datas,
            ArrayList<String> attrList ,int bestAttrIndex) {
        System.out.println("111");
        System.out.print("��ѡ�����б� ");
        for (int i = 0; i < attrList.size(); i++) {
            System.out.print(" " + attrList.get(i) + " ");
        }
       
        System.out.println();
        System.out.println("bestAttrIndex"+bestAttrIndex);
        TreeNode node = new TreeNode();
        node.setDatas(datas);
        node.setCandAttr(attrList);
        Map<String, Integer> classes = classOfDatas(datas,bestAttrIndex);
        Map<String, Integer> results = classOfDatas(datas,attrList.size()-1);
        System.out.println(classes);// #

        String maxC = maxClass(classes);

        System.out.println(maxC);// #

        System.out.println("��ŷ������͵ĸ�����" + classes.size());
        System.out.println("ʣ��������Ϊ" + attrList.size());
        if ( attrList.size() == 1) {
            node.setName(maxC);


            return node;
        }

        Gain gain = new Gain(datas, attrList);
        bestAttrIndex = gain.bestGainAttrIndex(results);
        System.out.println("��ѷ�����������Ϊ" + attrList.get(bestAttrIndex));// #
        ArrayList<String> rules = gain.getValues(datas, bestAttrIndex);
        System.out.println("�������Ϊ" + rules);// #
        node.setRule(rules);
        node.setName(attrList.get(bestAttrIndex));

        attrList.remove(bestAttrIndex);

        ArrayList<ArrayList<ArrayList<String>>> allDatas=new ArrayList<ArrayList<ArrayList<String>>>() ;
        for (int i = 0; i < rules.size(); i++) {
            String rule = rules.get(i);

            ArrayList<ArrayList<String>> di = gain.datasOfValue(bestAttrIndex,
                    rule);
            allDatas.add(di);
        }
        for(int i=0;i<allDatas.size();i++){
            for (int j = 0; j < allDatas.get(i).size(); j++) {
                allDatas.get(i).get(j).remove(bestAttrIndex);
            }


            System.out.println("ʣ���������Ϊ" + attrList);// #
            System.out.println();
            if (allDatas.get(i).size() == 0 || attrList.size() == 0) {
                TreeNode leafNode = new TreeNode();
                leafNode.setName(maxC);
                leafNode.setDatas(allDatas.get(i));
                leafNode.setCandAttr(attrList);
                node.getChild().add(leafNode);
            } else {

                TreeNode newNode = buildTree(allDatas.get(i), attrList,bestAttrIndex);

                node.getChild().add(newNode);

            }

        }

        return node;
    }

}