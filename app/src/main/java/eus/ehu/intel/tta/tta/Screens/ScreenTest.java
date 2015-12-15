package eus.ehu.intel.tta.tta.Screens;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import eus.ehu.intel.tta.tta.DataType.Test;
import eus.ehu.intel.tta.tta.R;

public class ScreenTest extends ScreensBase{
    private final static String TAG = ScreenTest.class.getCanonicalName();

    private Button screen_menu_button_tracing;
    private RadioGroup screen_test_RadioGroup_items;
    private TextView screen_test_TextView_question;
    private int positionSelect=-1;
    private ArrayList<RadioButton> radioButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_test);


        //Generamos test ejemplo;
        ArrayList<String> items=new ArrayList<String>();
        items.add("prueba1");
        items.add("prueba2");
        items.add("prueba3");
        items.add("prueba4");
        Test test=new Test(items,items.get(0),"PREGUNTA");
        dataNow.addTests(test);


        screen_test_TextView_question=(TextView)findViewById(R.id.screen_test_TextView_question);
        screen_menu_button_tracing=(Button)findViewById(R.id.screen_menu_button_tracing);
        screen_test_RadioGroup_items=(RadioGroup)findViewById(R.id.screen_test_RadioGroup_items);

        screen_test_TextView_question.setText(dataNow.getTests().get(0).getQuestion());
        radioButtons=new ArrayList<RadioButton>();
        if(dataNow!=null && dataNow.getTests().get(0).getItems()!=null){

            for(int con=0;con<dataNow.getTests().get(0).getItems().size();con++){
                RadioButton radioButton=new RadioButton(this);
                radioButton.setText(dataNow.getTests().get(0).getItems().get(con));
                radioButtons.add(radioButton);
                screen_test_RadioGroup_items.addView(radioButton);
            }
        }

        screen_test_RadioGroup_items.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d(TAG, "id" + checkedId);
                RadioButton radio=(RadioButton)findViewById(checkedId);
                int index=screen_test_RadioGroup_items.indexOfChild(radio);
                if(index==dataNow.getTests().get(0).getSolutionPosition()){
                    //Es correcto

                }else{
                    //Es false
                }
                screen_menu_button_tracing.setVisibility(View.VISIBLE);
                positionSelect=index;
            }

        });





        screen_menu_button_tracing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = screen_test_RadioGroup_items.getCheckedRadioButtonId();
                for(int con=0;con<radioButtons.size();con++){
                    radioButtons.get(con).setEnabled(false);
                }
                if (positionSelect == -1) {
                    return;
                }
                if (positionSelect == dataNow.getTests().get(0).getSolutionPosition()) {
                    //Es correcto
                    Log.d(TAG, getString(R.string.OK));
                    Toast.makeText(getApplicationContext(), getString(R.string.OK), Toast.LENGTH_SHORT).show();
                    radioButtons.get(positionSelect).setBackgroundColor(Color.GREEN);
                } else {
                    //Es false
                    Log.d(TAG, getString(R.string.FAIL));
                    Toast.makeText(getApplicationContext(), getString(R.string.FAIL), Toast.LENGTH_SHORT).show();
                    radioButtons.get(positionSelect).setBackgroundColor(Color.RED);
                    radioButtons.get(dataNow.getTests().get(0).getSolutionPosition()).setBackgroundColor(Color.GREEN);
                }

            }
        });
    }


}
