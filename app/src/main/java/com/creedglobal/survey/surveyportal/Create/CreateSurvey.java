package com.creedglobal.survey.surveyportal.Create;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.creedglobal.survey.surveyportal.MainScreen;
import com.creedglobal.survey.surveyportal.R;

public class CreateSurvey extends AppCompatActivity {
    private ViewGroup mContainerView;
    int q=0;
    EditText question_edit, opt1_edit, opt2_edit, opt3_edit, opt4_edit, surveyname_edit;
    TextView qid_text;
    String surveyName = null;
    int qid = 0,maxq=5,minq=3;
    String question,opt1,opt2,opt3,opt4,opt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_survey);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContainerView = (ViewGroup) findViewById(R.id.container);
        surveyname_edit = (EditText) findViewById(R.id.surveyname_edit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.create_survey, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Navigate "up" the demo structure to the launchpad activity.
                // See http://developer.android.com/design/patterns/navigation.html for more.
                NavUtils.navigateUpTo(this, new Intent(this, MainScreen.class));
                return true;

            case R.id.action_add_item:
                // Hide the "empty" view since there is now at least one item in the list.

                if (qid < maxq) {
                    addItem();
                } else {
                    Toast.makeText(this, "You cannot add more questions", Toast.LENGTH_LONG).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addItem() {
        surveyName = surveyname_edit.getText().toString();
        if (surveyName.length() < 2||surveyName.isEmpty()) {
            surveyname_edit.setError("please enter valid survey name");
        }
        else {

            // Instantiate a new "row" view.
            final ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.list_item_example, mContainerView, false);
            qid_text = (TextView) newView.findViewById(R.id.qidtxt);
            question_edit = (EditText) newView.findViewById(R.id.editText4);
            question_edit.requestFocus();
            opt1_edit = (EditText) newView.findViewById(R.id.editText5);
            opt2_edit = (EditText) newView.findViewById(R.id.editText6);
            opt3_edit = (EditText) newView.findViewById(R.id.editText7);
            opt4_edit = (EditText) newView.findViewById(R.id.editText8);

            // setting id to all view at run time and generating "Question no." i.e. "qid"
            qid_text.setText("Question " + ++qid + ".");

            question_edit.setId(++q);
            opt1_edit.setId(++q);
            opt2_edit.setId(++q);
            opt3_edit.setId(++q);
            opt4_edit.setId(++q);

//        Log.i("infoo",edit1.getText().toString());
//            Log.i("infoo", "qid " + (qid));
//            Log.i("infoo", "question id " + question_edit.getId());
//            Log.i("infoo", "option1 id " + opt1_edit.getId());
//            Log.i("infoo", "option2 id " + opt2_edit.getId());
//            Log.i("infoo", "option3 id " + opt3_edit.getId());
//            Log.i("infoo", "option4 id " + opt4_edit.getId());
//            Log.i("y",""+opt4_edit.getText().toString());

            // Adding view to view to container.  i.e. adding "list_item_example.xml" >>> "R.id.container"
            mContainerView.addView(newView, 0);
        }
    }

    public void done(View view) {
        view.setClickable(false);

        if (qid<minq){
            Toast.makeText(this,"please add atleast 3 questions",Toast.LENGTH_LONG).show();
        }
        else {

            Log.i("my_infoo","Done button is Clicked....");
            int  eid = 0;
            for (int j = 0; j < qid; j++) {
//                Log.i("my_info","nested loop qid "+qid);
                eid++;
                EditText question_tmp = (EditText)findViewById(eid);
                eid++;
                EditText option1_tmp = (EditText)findViewById(eid);
                eid++;
                EditText option2_tmp = (EditText)findViewById(eid);
                eid++;
                EditText option3_tmp = (EditText)findViewById(eid);
                eid++;
                EditText option4_tmp = (EditText)findViewById(eid);

//                Capturing Question & options from UI . (1 by 1)
                question=question_tmp.getText().toString();
                opt1=option1_tmp.getText().toString();
                opt2=option2_tmp.getText().toString();
                opt3=option3_tmp.getText().toString();
                opt4=option4_tmp.getText().toString();


//                Log.i("my_info","Q n Options "+question+","+opt1+","+opt2+","+opt3+","+opt4);
//                Log.i("infoo", "Question " + question_tmp.getId()+question_tmp.getText().toString());
//                Log.i("infoo", "op 1 "+option1_tmp.getId()+option1_tmp.getText().toString());
//                Log.i("infoo", "op 2 "+option2_tmp.getId()+option2_tmp.getText().toString());
//                Log.i("infoo", "op 3 "+option3_tmp.getId()+option3_tmp.getText().toString());
//                Log.i("infoo", "op 4 "+option4_tmp.getId()+option4_tmp.getText().toString());
            }
        }

    }
}