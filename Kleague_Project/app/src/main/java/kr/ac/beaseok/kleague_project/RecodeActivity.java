package kr.ac.beaseok.kleague_project;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class RecodeActivity extends AppCompatActivity {

    DatePicker dp; //class import
    EditText edtDiary;
    Button btnWrite;
    String fileName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recode);//
        setTitle("기록하기");
        dp = (DatePicker) findViewById(R.id.datePicker1);
        edtDiary = (EditText) findViewById(R.id.edtDiary);
        btnWrite = (Button) findViewById(R.id.btnWrite);
        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();//SecondActivity=>본 화면을 종료하는 메소드
            }
        });

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {

                fileName = Integer.toString(i) + "_" + Integer.toString(i1 + 1) + "_" +
                        Integer.toString(i2) + ".txt";

                String str = readDiary(fileName);//여기서 호출합니다.

                edtDiary.setText(str);

                btnWrite.setEnabled(true);
            }
        });
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_PRIVATE);//화일을 쓰기

                    String str = edtDiary.getText().toString();
                    //파일에 write() 메소드로 쓴다.
                    outFs.write(str.getBytes());
                    outFs.close();

                    Toast.makeText(getApplicationContext(), fileName + "처리됨",
                            Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                }
            }//onClick
        });

    }//onCreate

    String readDiary(String fName) {

        String diaryStr = null;
        FileInputStream inFs;
        try {
            inFs = openFileInput(fName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            diaryStr = (new String(txt)).trim();
            btnWrite.setText("수정하기");

        } catch (IOException e) {
            edtDiary.setHint("기록이 없습니다.");
            btnWrite.setText("새로저장");
        }
        return diaryStr;

    }
}
