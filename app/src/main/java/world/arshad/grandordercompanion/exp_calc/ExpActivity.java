package world.arshad.grandordercompanion.exp_calc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.SidebarActivity;
import world.arshad.grandordercompanion.data.domain_data.sources.DomainDataSingleton;

public class ExpActivity extends SidebarActivity {

    @BindView(R.id.exp_start_spinner)
    Spinner expStartSpinner;

    @BindView(R.id.exp_end_spinner)
    Spinner expEndSpinner;

    @BindView(R.id.exp_calc_button)
    Button expCalcButton;

    private Context c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp);
        super.setUpSidebar();
        ButterKnife.bind(this);

        expEndSpinner.setEnabled(false);

        ArrayAdapter<Integer> startAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item,
                ContiguousSet.create(Range.closed(1, 100), DiscreteDomain.integers()).asList());
        startAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        expStartSpinner.setAdapter(startAdapter);

        expStartSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<Integer> endAdapter = new ArrayAdapter<Integer>(c,
                        android.R.layout.simple_spinner_item,
                        ContiguousSet.create(Range.closed(i + 1, 100), DiscreteDomain.integers()).asList());
                endAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                expEndSpinner.setAdapter(endAdapter);
                expEndSpinner.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                expEndSpinner.setEnabled(false);
            }
        });


        expCalcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int start = (Integer) expStartSpinner.getSelectedItem();
                int end = (Integer) expEndSpinner.getSelectedItem();

                int needed = DomainDataSingleton.getInstance().getExpEntries().get(end - 1).getTotalExp() - DomainDataSingleton.getInstance().getExpEntries().get(start - 1).getTotalExp();

                int allCardsNeeded = (int) Math.ceil((double) needed / 32400);
                int generalCardsNeeded = (int) Math.ceil((double) needed / 27000);

                String message = String.format("%d Class/All | %d Other", allCardsNeeded, generalCardsNeeded);

                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setTitle("You need...");
                builder.setMessage(message);
                builder.setCancelable(true);

                builder.setNeutralButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });

    }
}
