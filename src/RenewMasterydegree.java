import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class RenewMasterydegree {
//
//	static class GraphMatrix{
//        static final int MaxNum=20;
//        Map<String,Integer> Vertex=new HashMap<String,Integer>();
//        Map<Integer,String> VertexTravse=new HashMap<Integer,String>();
////        char[][] Vertex=new char[MaxNum][MaxNum];  //���涥����Ϣ����Ż���ĸ��
//        int GType;    //ͼ�����ͣ�0������ͼ��1������ͼ��
//        int VertexNum;        //���������
//        int EdgeNum;        //�ߵ�����
//        int[][] EdgeWeight=new int[MaxNum][MaxNum];
//        int[] PointWeight=new int[MaxNum];        //����ߵ�Ȩ
//        int[] isTrav=new int[MaxNum];        //������־
//    }
//	static final int MaxValue=65535;    //���ֵ������Ϊһ�����������
//    static int[] path=new int[GraphMatrix.MaxNum];    //���㾭���Ķ��㼯�ϵ�����
//    static int[] tmpvertex=new int[GraphMatrix.MaxNum];  //���·������ʼ�㼯��
//    static void CreateGraph(GraphMatrix GM){
//        int i,j,k;
//        int weight;     //Ȩ
//        char EstartV,EendV;        //�ߵ���ʼ����
//        System.out.printf("����ͼ�и�������Ϣ\n");
//        GM.Vertex.put("��������", 1);  GM.VertexTravse.put(1, "��������");
//        GM.Vertex.put("���ֺ�������",2); GM.VertexTravse.put(2, "���ֺ�������");
//        GM.Vertex.put("���ݺ�������",3); GM.VertexTravse.put(3, "���ݺ�������");
//        GM.Vertex.put("1NF",4); GM.VertexTravse.put(4, "1NF");
//        GM.Vertex.put("2NF",5); GM.VertexTravse.put(5, "2NF");
//        GM.Vertex.put("3NF",6); GM.VertexTravse.put(6, "3NF");
//        GM.Vertex.put("BCNF",7); GM.VertexTravse.put(7, "BCNF");
//        GM.Vertex.put("��������",8); GM.VertexTravse.put(8, "��������");
//        GM.Vertex.put("��",9); GM.VertexTravse.put(9, "��");
//        GM.Vertex.put("������",10); GM.VertexTravse.put(10, "������");
//        GM.Vertex.put("��������",11); GM.VertexTravse.put(11, "��������");
//        
//        GM.EdgeNum=11;
//        GM.EdgeWeight[1][2]=1;
//        GM.EdgeWeight[1][3]=1;
//        GM.EdgeWeight[2][5]=1;
//        GM.EdgeWeight[4][5]=1;
//        GM.EdgeWeight[3][6]=1;
//        GM.EdgeWeight[5][6]=1;
//        GM.EdgeWeight[6][7]=1;
//        GM.EdgeWeight[7][8]=1;
//        GM.EdgeWeight[8][9]=1;
//        GM.EdgeWeight[9][10]=1;
//        GM.EdgeWeight[9][11]=1;
//      
//    }
//   
//    static void distMin(GraphMatrix GM,int vend){    //vendΪ������
//        int[] weight=new int[GraphMatrix.MaxNum];        //ĳ��ֹ�㵽����������·������
//        int i,j,k,min;
//        vend--;
//        for(i=0;i<GM.VertexNum;i++){        //��ʼ��weight����
//            weight[i]=GM.EdgeWeight[vend][i];
//        }
//        for(i=0;i<GM.VertexNum;i++){        //��ʼ��path����
//            if(weight[i]<MaxValue&&weight[i]>0){    //��ЧȨֵ
//                path[i]=vend;
//            }
//        }
//        for(i=0;i<GM.VertexNum;i++){        //��ʼ��tmpvertex����
//            tmpvertex[i]=0;            //��ʼ�����㼯��Ϊ��
//        }
//        tmpvertex[vend]=1;        //ѡ�붥��vend
//        weight[vend]=0;
//        for(i=0;i<GM.VertexNum;i++){        //����δ�ö������СȨֵ
//            min=MaxValue;
//            k=vend;
//            for(j=0;j<GM.VertexNum;j++){
//                if(tmpvertex[j]==0&&weight[j]<min){
//                    min=weight[j];
//                    k=j;
//                }
//            }
//            tmpvertex[k]=1;            //������kѡ��
//            for(j=0;j<GM.VertexNum;j++){        //�Զ���kΪ�м�㣬���¼���Ȩֵ
//                if(tmpvertex[j]==0&&weight[k]+GM.EdgeWeight[k][j]<weight[j]){
//                    weight[j]=weight[k]+GM.EdgeWeight[k][j];
//                    path[j]=k;
//                }
//            }
//        }
//    }
//    public void finddist(int vend){
//        GraphMatrix GM=new GraphMatrix();   //���屣���ڽӱ�ṹ��ͼ
//        int i,k;
//        System.out.println("������·�����⣡");
//            GM.GType=1; //ͼ������
//            CreateGraph(GM);        //�����ڽӱ�ṹ��ͼ
//            
//            distMin(GM,vend);
//            vend--;
//            System.out.printf("\n�����㵽�ﶥ��%c�����·���ֱ�Ϊ����ʼ��-�����㣩��\n",GM.VertexTravse.get(vend));
//            for(i=0;i<GM.VertexNum;i++){        //������
//                if(tmpvertex[i]==1){
//                    k=i;
//                    while(k!=vend){
//                        System.out.printf("����%c-", GM.VertexTravse.get(k));
//                        k=path[k];
//                    }
//                    System.out.printf("����%c\n", GM.VertexTravse.get(k));
//                }else{
//                    System.out.printf("%c-%c:��·��\n", GM.VertexTravse.get(i),GM.VertexTravse.get(vend));
//                }
//            }
//    }
	private static int INF = Integer.MAX_VALUE;
    //dist[i][j]=INF<==>i �� j֮��û�б�
    private int[][] dist;
    //����i �� j�����·�����ȣ���ֵ��i��j�ıߵ�Ȩ��
    private int[][] path;
    private List<Integer> result = new ArrayList<Integer>();
    public RenewMasterydegree(int size) {
        this.path = new int[size][size];
        this.dist = new int[size][size];
    }
    public RenewMasterydegree() {
    }
    public Map<String,Double> renew(ArrayList<String> recommendlist,Map<String,Double>concepterate) {
    	RenewMasterydegree  graph = new RenewMasterydegree(11);
    	//double average=graph.getAverage(kgrate); //ԭ����ƽ��ֵ
    //	double standarderror=graph.getStandardDevition(kgrate, average);
    	int dist=0;
    	double sigma=1.5;
    	int[][] matrix =
            {  {1,1,INF,INF,INF,INF,INF,INF,INF,INF,INF},
               {INF,1,1,INF,INF,INF,INF,INF,INF,INF,INF},
               {INF,INF,1,1,INF,INF,INF,INF,INF,INF,INF},
               {INF,INF,INF,1,INF,INF,1,INF,INF,INF,INF},
               {INF,INF,INF,INF,1,INF,INF,INF,INF,INF,INF},
               {INF,INF,1,INF,INF,1,INF,INF,INF,INF,INF},
               {INF,INF,INF,INF,INF,INF,1,INF,1,INF,INF},
               {INF,INF,INF,INF,INF,1,INF,1,INF,1,INF},
               {INF,INF,INF,INF,1,INF,INF,INF,1,INF,1},
               {INF,1,INF,INF,INF,INF,INF,INF,INF,1,INF},   //֪ʶͼ��
               {INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,1}};   
    	HashMap<String,Integer> knowledgepoint=new HashMap<String,Integer>();
    	HashMap<Integer,String> knowledgepointtravse=new HashMap<Integer,String>();
    	Map<String,Double>kgrate=new HashMap<String,Double>();
    
    	kgrate=concepterate;
    	knowledgepoint.put("��������", 8);  knowledgepointtravse.put(8,"��������");
    	knowledgepoint.put("���ֺ�������",10); knowledgepointtravse.put(10,"���ֺ�������");
    	knowledgepoint.put("���ݺ�������",6); knowledgepointtravse.put(6,"���ݺ�������");
    	knowledgepoint.put("1NF",1); knowledgepointtravse.put(1,"1NF");
    	knowledgepoint.put("2NF",2); knowledgepointtravse.put(2,"2NF");
    	knowledgepoint.put("3NF",3);knowledgepointtravse.put(3,"3NF");
    	knowledgepoint.put("BCNF",4); knowledgepointtravse.put(4,"BCNF");
    	knowledgepoint.put("��������",7);knowledgepointtravse.put(7,"��������");
    	knowledgepoint.put("��",9); knowledgepointtravse.put(9,"��");
    	knowledgepoint.put("������",5); knowledgepointtravse.put(5,"������");
    	knowledgepoint.put("��������",11);knowledgepointtravse.put(11,"��������");
//    	for(int i=1;i<=11;i++)
//        {
//           	kgrate.put(knowledgepointtravse.get(i), concepterate.get(knowledgepointtravse.get(i)));
//            //System.out.println("123"+kgrate);
//        }
       // System.out.println("123"+kgrate);

        for(int i=0;i<recommendlist.size();i++)
        {
        	int begin=knowledgepoint.get(recommendlist.get(i))-1;
        	double KGrate=kgrate.get(recommendlist.get(i));
        	for(int j=1;j<=11;j++)
        	{
        		 graph.findCheapestPath(begin, j-1, matrix);
        		 String end=knowledgepointtravse.get(j);
        		 List<Integer> list = graph.result;
        		 dist=graph.dist[begin][j-1];
        		 //System.out.println("KGrate="+KGrate+" "+kgrate.toString()+" kgrate(j)="+kgrate.get(end)+" end="+end);
        		 
//        		 KGrate=KGrate+ kgrate.get(end)*( Math.exp( -(dist*dist)/ (2 * sigma * sigma) ) );       //��������/ Math.sqrt(2 * Math.PI * sigma * sigma)   //KGrate=KGrate+KGtrate*(1/())
        		 KGrate=KGrate+ ( Math.exp( -(dist*dist)/ (2 * sigma * sigma) ) );
//        		 kgrate.put(recommendlist.get(i), KGrate);
//        		 double max=getMax(kgrate);
//        	     double min=getMin(kgrate);
//        		 KGrate=((double)(KGrate-min))/(float)(max-min);

        	}
        	//KGrate=(KGrate-average)/standarderror;
        	
            kgrate.put(recommendlist.get(i), KGrate);
            //System.out.println(kgrate.toString());
        }
       // System.out.println(kgrate.toString());
//        double max=getMax(kgrate);
//        double min=getMin(kgrate);
//        System.out.println("min="+min+" max="+max);
//        for(int i=1;i<=11;i++)
//        {
//        	double KGrate=kgrate.get(knowledgepointtravse.get(i));
//        	KGrate=(KGrate-min)/(max-min);
//        	kgrate.put(knowledgepointtravse.get(i), KGrate);
//        }
//        System.out.println(kgrate.toString());
        return kgrate;
//       
//        System.out.println(begin + " to " + end + ",the cheapest path is:");
//        System.out.println(list.toString());
//        System.out.println(graph.dist[begin][end]);
    }
    public double getMax(Map<String,Double>kgrate){
    	double max= 0;
        Iterator iter = kgrate.entrySet().iterator();
        while (iter.hasNext()) {
        	Map.Entry entry = (Map.Entry) iter.next();
        	Object key = entry.getKey();
        	Object val = entry.getValue();
        	if(Double.parseDouble(val.toString())>max)
        	{
        		max=Double.parseDouble(val.toString());
        		 //System.out.println(" max="+max);
        	}
        }
        return max;
    }
    public double getMin(Map<String,Double>kgrate){
        	double min= INF;
            Iterator iter = kgrate.entrySet().iterator();
            while (iter.hasNext()) {
            	Map.Entry entry = (Map.Entry) iter.next();
            	Object key = entry.getKey();
            	Object val = entry.getValue();
            	if(Double.parseDouble(val.toString())<min)
            	{
            		min=Double.parseDouble(val.toString());
            		 //System.out.println("min="+min);
            	}
            }
            return min;
        }
    public double getStandardDevition(Map<String,Double>kgrate,double average){
    	double sum = 0;
        Iterator iter = kgrate.entrySet().iterator();
        while (iter.hasNext()) {
        	Map.Entry entry = (Map.Entry) iter.next();
        	Object key = entry.getKey();
        	Object val = entry.getValue();
        	sum +=Math.sqrt((Double.parseDouble(val.toString()) -average) *(Double.parseDouble(val.toString())-average));
      }
        return (sum / 10);
    }
    public void findCheapestPath(int begin, int end, int[][] matrix) {
        floyd(matrix);
        result.add(begin);
       // System.out.println("j="+end);
        findPath(begin, end);
        result.add(end);
    }
 
    public void findPath(int i, int j) {
    	//System.out.println("j="+j+" i="+i);
        int k = path[i][j];
        if (k == -1) {
            return;
        }
        findPath(i, k);   //�ݹ�
        result.add(k);
        findPath(k, j);
    }
 
    public void floyd(int[][] matrix) {
        int size = matrix.length;
        //initialize dist and path   
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                path[i][j] = -1;
                dist[i][j] = matrix[i][j];
            }
        }
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (dist[i][k] != INF &&
                            dist[k][j] != INF &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }
 
    }
 
   
}
