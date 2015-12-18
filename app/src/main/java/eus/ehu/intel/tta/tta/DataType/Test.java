package eus.ehu.intel.tta.tta.DataType;

import java.util.ArrayList;

/**
 * Created by alumno on 15/12/15.
 */

public class Test {
    public  final static int HTML_HELP=1;
    public final static int URL_HELP=2;
    public  final static int VIDEO_HELP=3;
    public final static int AUDIO_HELP=4;
    private int typeHelp=-1;
    private ArrayList<String> items=null;
    private String solution=null;
    private String question=null;
    public String help=null;

    public Test(){
        items=new ArrayList<>();
        solution=new String();
        question=new String();
    }

    public Test(ArrayList<String> items,String solution,String question,String help,int typeHelp){
        this.items=items;
        this.solution=solution;
        this.question=question;
        this.help=help;
        this.typeHelp=typeHelp;
    }

    public Test(ArrayList<String> items,int solutionPosition,String question,String help,int typeHelp){
        this.items=items;
        this.question=question;
        if(solutionPosition<items.size()){
            solution=items.get(solutionPosition);
        }
        this.help=help;
        this.typeHelp=typeHelp;

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

    public String getHelp(){
        return help;
    }

    public void setHelp(String help){
        this.help=help;
    }


    public int getTypeHelp(){
        return typeHelp;
    }

    public void setTypeHelp(int typeHelp){
        this.typeHelp=typeHelp;
    }



}
