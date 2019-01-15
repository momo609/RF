import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Gain {
    private ArrayList<ArrayList<String>> D = null; // ѵ��Ԫ��
    private ArrayList<String> attrList = null; // ��ѡ���Լ�

    public Gain(ArrayList<ArrayList<String>> datas, ArrayList<String> attrList) {
        this.D = datas;
        this.attrList = attrList;
    }

    /**
     * ��ȡ��Ѻ�ѡ�������ϵ�ֵ�򣨼ٶ������������ϵ�ֵ�������޵����ʻ�������͵ģ�
     * 
     * @param attrIndex
     *            ָ���������е�����
     * @return ֵ�򼯺�
     */
    public ArrayList<String> getValues(ArrayList<ArrayList<String>> datas,
            int attrIndex) {
        ArrayList<String> values = new ArrayList<String>();
        String r = "";
        for (int i = 0; i < datas.size(); i++) {

            r = datas.get(i).get(attrIndex);
            if (!values.contains(r)) {
                values.add(r);
            }
        }
        return values;
    }

    /**
     * ��ȡָ�����ݼ���ָ����������������ֵ�������
     * 
     * @param d
     *            ָ�������ݼ�
     * @param attrIndex
     *            ָ��������������
     * @return ����������map
     */
    public Map<String, Integer> valueCounts(ArrayList<ArrayList<String>> datas,
            int attrIndex) {
        Map<String, Integer> valueCount = new HashMap<String, Integer>();
        String c = "";
        ArrayList<String> tuple = null;
        for (int i = 0; i < datas.size(); i++) {
//            System.out.println(datas.get(i));
            tuple = datas.get(i);

            c = tuple.get(attrIndex);
            if (valueCount.containsKey(c)) {
                valueCount.put(c, valueCount.get(c) + 1);
            } else {
                valueCount.put(c, 1);
            }
        }
        return valueCount;

    }
    
    public Map<String, Integer> valueCountsy(ArrayList<ArrayList<String>> datas,
            int attrIndex) {
        Map<String, Integer> valueCount = new HashMap<String, Integer>();
        String c = "";
        ArrayList<String> tuple = null;
        for (int i = 0; i < datas.size(); i++) {
//            System.out.println(datas.get(i));
            tuple = datas.get(i);

            c = tuple.get(attrIndex);
            if (valueCount.containsKey(c)&&tuple.get(tuple.size()-1).equals("yes")) {
                valueCount.put(c, valueCount.get(c) + 1);
            } else {
                valueCount.put(c, 1);
            }
        }
        return valueCount;

    }
    
    public Map<String, Integer> valueCountsn(ArrayList<ArrayList<String>> datas,
            int attrIndex) {
        Map<String, Integer> valueCount = new HashMap<String, Integer>();
        String c = "";
        ArrayList<String> tuple = null;
        for (int i = 0; i < datas.size(); i++) {
//            System.out.println(datas.get(i));
            tuple = datas.get(i);

            c = tuple.get(attrIndex);
            if (valueCount.containsKey(c)&&tuple.get(tuple.size()-1).equals("no")) {
                valueCount.put(c, valueCount.get(c) + 1);
            } else {
                valueCount.put(c, 1);
            }
        }
        return valueCount;

    }

    /**
     * ���datas��Ԫ����������������Ϣ����datas����
     * 
     * @param datas
     *            ѵ��Ԫ��
     * @return datas����ֵ
     */
    public double infoD(ArrayList<ArrayList<String>> datas, Map<String, Integer> results) {
        double info = 0.000;
        int total = datas.size();

        Map<String, Integer> classes = valueCounts(datas, attrList.size()-1);
        Map<String, Integer> classesy = valueCountsy(datas, attrList.size()-1);
        Map<String, Integer> classesn = valueCountsn(datas, attrList.size()-1);
        Iterator<Map.Entry<String, Integer>> iter = classes.entrySet().iterator();
        Iterator<Map.Entry<String, Integer>> iter2 = classesy.entrySet().iterator();
        Iterator<Map.Entry<String, Integer>> iter3 = classesn.entrySet().iterator();
        Integer[] counts = new Integer[classes.size()];
        Integer[] countsy = new Integer[classesy.size()];
        Integer[] countsn = new Integer[classesn.size()];
        for (int i = 0; iter.hasNext(); i++) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) iter.next();
            Integer val = (Integer) entry.getValue();
            counts[i] = val;
        }
        for (int i = 0; iter2.hasNext(); i++) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) iter2.next();
            Integer val = (Integer) entry.getValue();
            countsy[i] = val;
        }
        for (int i = 0; iter3.hasNext(); i++) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) iter3.next();
            Integer val = (Integer) entry.getValue();
            countsn[i] = val;
        }
        for (int i = 0; i < counts.length; i++) {
            double base = DecimalCalculate.div(counts[i], total, 3);
            info += (-1) * base * Math.log(base);
        }
        return info;
    }

    /**
     * ��ȡָ����������ָ��ֵ�������Ԫ��
     * 
     * @param attrIndex
     *            ָ������������
     * @param value
     *            ָ�������е�ֵ��
     * @return ָ����������ָ��ֵ�������Ԫ��
     */
    public ArrayList<ArrayList<String>> datasOfValue(int attrIndex, String value) {
        ArrayList<ArrayList<String>> Di = new ArrayList<ArrayList<String>>();
        ArrayList<String> t = null;
        for (int i = 0; i < D.size(); i++) {
            t = D.get(i);

            if (t.get(attrIndex).equals(value)) {
                Di.add(t);
            }
        }
        return Di;
    }

    /**
     * ���ڰ�ָ�����Ի��ֶ�D��Ԫ���������Ҫ��������Ϣ
     * 
     * @param attrIndex
     *            ָ�����Ե�����
     * @return ��ָ�����Ի��ֵ�������Ϣֵ
     */
    public double infoAttr(int attrIndex, Map<String, Integer> results) {
        double info = 0.000;
        ArrayList<String> values = getValues(D, attrIndex);
        for (int i = 0; i < values.size(); i++) {
            ArrayList<ArrayList<String>> dv = datasOfValue(attrIndex,
                    values.get(i));
            info += DecimalCalculate.mul(
                    DecimalCalculate.div(dv.size(), D.size(), 3), infoD(dv,results));
        }
        return info;
    }

    /**
     * ��ȡ��ѷ������Ե�����
     * 
     * @return ��ѷ������Ե�����
     */
    public int bestGainAttrIndex(Map<String, Integer> results) {
        int index = 0;
        double gain = 0.000;
        double tempGain = 0.000;
        for (int i = 0; i < attrList.size(); i++) {

            tempGain = infoD(D,results) - infoAttr(i,results);


            if (tempGain > gain) {
                gain = tempGain;
                index = i;
            }
        }
        return index;
    }

}
