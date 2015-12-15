package eus.ehu.intel.tta.tta.DataType;

import java.util.ArrayList;

/**
 * Created by alumno on 15/12/15.
 */
public class Data {
    public ArrayList<Test> tests=null;

    public Data(ArrayList<Test> tests){
        this.tests=tests;
    }

    public Data(){
        tests=new ArrayList<Test>();
    }

    public ArrayList<Test> getTests(){
        return tests;
    }

    public Test getTest(int position){
        if(tests.size()>position && position>=0){
            return tests.get(position);
        }
        return null;
    }

    public void setTests(ArrayList<Test> tests){
        this.tests=tests;
    }

    public void addTests(Test test){
        this.tests.add(test);
    }


}
