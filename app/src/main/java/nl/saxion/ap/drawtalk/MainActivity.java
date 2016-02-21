package nl.saxion.ap.drawtalk;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import java.util.ArrayList;

import nl.saxion.ap.drawtalk.R;
import nl.saxion.ap.drawtalk.model.PiePart;
import nl.saxion.ap.drawtalk.model.PieView;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout rlPie;
    private Button btnAdd;
    private EditText etTopic;
    private PieView pvPie;
    private ArrayList<Integer> colors;
    private int partCount = 0;
    private int totalParts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init colors
        colors = new ArrayList<Integer>();
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.CYAN);
        colors.add(Color.MAGENTA);

        pvPie = (PieView) findViewById(R.id.pvPie);

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



    }

}
