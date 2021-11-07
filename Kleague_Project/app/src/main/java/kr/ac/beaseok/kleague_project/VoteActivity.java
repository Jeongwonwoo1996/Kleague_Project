package kr.ac.beaseok.kleague_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VoteActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote);
        setTitle("K리그1 선수 투표");

        Button btnReturn = (Button) findViewById(R.id.btnReturn);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final int voteCount[] = new int[9];
        for (int i = 0; i < 9; i++) voteCount[i] = 0;

        ImageView image[] = new ImageView[9];
        Integer imageId[] = {R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4,
                R.id.iv5, R.id.iv6, R.id.iv7, R.id.iv8, R.id.iv9};
        final String imgName[] = {"주니오(울산)", "일류첸코(포항)",
                "세징야(대구)", "팔로세비치(포항)", "한교원(전북)",
                "안토니스(수원)", "타카트(수원)", "윤빛가람(울산)",
                "강상우(상주)"};
        for (int i = 0; i < imageId.length; i++) {

            final int index;
            index = i;

            image[index] = (ImageView) findViewById(imageId[index]);

            image[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    voteCount[index]++;

                    Toast.makeText(getApplicationContext(), imgName[index] + ": 총" +
                            voteCount[index] + "표", Toast.LENGTH_SHORT).show();

                }
            });
        }
        Button btnFinish = (Button) findViewById(R.id.btnResult);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        ResultActivity.class);
                intent.putExtra("VoteCount", voteCount);
                intent.putExtra("ImageName", imgName);
                startActivity(intent);

            }
        });
    }//onCreate
}

