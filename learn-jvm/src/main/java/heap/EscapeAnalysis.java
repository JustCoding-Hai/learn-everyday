package heap;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 逃逸分析
 * @create 2021/10/5 - 15:50
 **/
public class EscapeAnalysis {

    public EscapeAnalysis obj;


    /**
     * 方法返回EscapeAnalysis，发生逃逸
     *
     * @return
     */
    public EscapeAnalysis getInstance() {
        return obj == null ? new EscapeAnalysis() : obj;
    }

    /**
     * 为成员属性赋值，发生逃逸
     */
    public void setObj() {
        this.obj = new EscapeAnalysis();

    }

    /**
     * 对象的作用域仅在当前方法中有效，没有发生逃逸
     */
    public void useEscapeAnalysis() {
        EscapeAnalysis escapeAnalysis = new EscapeAnalysis();
    }

    /**
     * 引用成员变量的值，发生逃逸
     */
    public void useEscapeAnalysis2() {
        EscapeAnalysis escapeAnalysis = getInstance();
    }


}
