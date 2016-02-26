package nl.saxion.ap.drawtalk;

import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import java.util.ArrayList;

import nl.saxion.ap.drawtalk.model.PiePart;
import nl.saxion.ap.drawtalk.view.PieView;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout rlPie;
    private Button btnAdd;
    private EditText etTopic;
    private PieView pvPie;
    private ArrayList<Integer> colors;
    private ArrayList<PiePart> pieParts;
    private int partCount = 0;
    private int totalParts = 0;
    private final String LOG_CAT = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_CAT,"onCreate");
        setContentView(R.layout.activity_main);

        // init colors
        colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.CYAN);
        colors.add(Color.MAGENTA);

        // init pieParts
        PiePart pie1 = new PiePart("NOUNS",25,Color.RED);
        PiePart pie2 = new PiePart("VERB",25,Color.YELLOW);
        PiePart pie3 = new PiePart("EMOTION",25,Color.GREEN);
        PiePart pie4 = new PiePart("ADJECTIVE",25,Color.BLUE);


        pvPie = (PieView) findViewById(R.id.pvPie);
        pvPie.addPart(pie1);
        pvPie.addPart(pie2);
        pvPie.addPart(pie3);
        pvPie.addPart(pie4);

        // init topic
        etTopic = (EditText) findViewById(R.id.etDescription);
        etTopic.setText("DOG");
        pvPie.setTopic(etTopic.getText().toString());


        // add onclick listener to add button
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SeekBar sbAmountParts = (SeekBar) findViewById(R.id.sbAmountParts);
                totalParts = sbAmountParts.getProgress();
                EditText etPart = (EditText) findViewById(R.id.etPart);
                PiePart pp1 = new PiePart(etPart.getText().toString(), 100 / totalParts, colors.get(partCount));
                partCount++;
                pvPie.addPart(pp1);

                // empty textfield
                etPart.setText("");

                // reset topic
                etTopic = (EditText) findViewById(R.id.etDescription);
                pvPie.setTopic(etTopic.getText().toString());

                // redraw
                pvPie.invalidate();
            }
        });

        // add change listener to main topic field
        etTopic.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // get changed topic
                pvPie.setTopic(s.toString());

                // redraw
                pvPie.invalidate();
            }
        });

        // redraw
        pvPie.invalidate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(LOG_CAT, "onConfigurationChanged");
        pvPie.setTopic(etTopic.getText().toString());
        pvPie.invalidate();
    }
}
