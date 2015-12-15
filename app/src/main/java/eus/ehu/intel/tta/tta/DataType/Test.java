package eus.ehu.intel.tta.tta.DataType;

import java.util.ArrayList;

/**
 * Created by alumno on 15/12/15.
 */

public class Test {
    private ArrayList<String> items=null;
    private String solution=null;
    private String question=null;

    public Test(){
        items=new ArrayList<>();
        solution=new String();
        question=new String();
    }

    public Test(ArrayList<String> items,String solution,String question){
        this.items=items;
        this.solution=solution;
        this.question=question;
    }

    public Test(ArrayList<String> items,int solutionPosition,String question){
        this.items=items;
        this.question=question;
        if(solutionPosition<items.size()){
            solution=items.get(solutionPosition);
        }


    }

    public int getSolutionPosition(){
        int solutionPosition=-1;
        if(items==null || solution==null )
        return solutionPosition;

        for(int con=0;con<items.size();con++){
            if(items.get(con).compareToIgnoreCase(solution)==0){
                solutionPosition=con;
            }
        }
        return solutionPosition;
    }

    public ArrayList<String> getItems(){
        return items;
    }

    public void setItems(ArrayList<String> items){
        this.items=items;
    }

    public String getQuestion(){
        return question;
    }

    public void setQuestion(String question){
        this.question=question;
    }


}
