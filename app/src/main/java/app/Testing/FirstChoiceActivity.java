package app.Testing;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.red_ragnar.testing.Presenter;
import com.example.red_ragnar.testing.R;

import java.util.Map;

import static ru.bpc.mobilebanksdk.R.drawable.ic_currency_eur;
import static ru.bpc.mobilebanksdk.R.drawable.ic_currency_rub;
import static ru.bpc.mobilebanksdk.R.drawable.ic_currency_usd;

public class FirstChoiceActivity extends AppCompatActivity {
    ImageButton _usdFrom;
    ImageButton _usdTo;
    TextView _text;
    Presenter _prsnt;
    int i = 1, j = 1;
    String _from = "USD", _to ="USD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_choice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //_prsnt = new Presenter(this);
        _usdFrom = (ImageButton) findViewById(R.id.imageButton5);
        _usdTo = (ImageButton) findViewById(R.id.imageButton2);
        _text =(TextView) findViewById(R.id.textView8);
        _text.setText("");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        _usdFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _text.setText("");
                i++;
                if (i % 3 == 1) {
                    _usdFrom.setImageResource(ic_currency_rub);
                    _from = "RUR";
                }
                else if (i % 3 == 2) {
                    _usdFrom.setImageResource(ic_currency_eur);
                    _from = "EUR";
                }
                else {
                    _usdFrom.setImageResource(ic_currency_usd);
                    _from = "USD";
                }
                Map Data = _prsnt.Get_Data(_from,_to);
                _text.append("base" + ": " + Data.get("base")+ "\n");
                _text.append("buy" + ": " + Data.get("buy")+ "\n");
                _text.append("sell" + ": " + Data.get("sell")+ "\n");
            }
        });
        _usdTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _text.setText("");
               j++;
                if (j % 3 == 1) {
                    _usdTo.setImageResource(ic_currency_rub);
                    _to = "RUR";
                }
                else if (j % 3 == 2) {
                    _usdTo.setImageResource(ic_currency_eur);
                    _to = "EUR";
                }
                else {
                    _usdTo.setImageResource(ic_currency_usd);
                    _to = "USD";
                }
                Map Data = _prsnt.Get_Data(_from,_to);
                _text.append("base" + ": " + Data.get("base") + "\n");
                _text.append("buy" + ": " + Data.get("buy")+ "\n");
                _text.append("sell" + ": " + Data.get("sell")+ "\n");

            }
        });
    }
}
