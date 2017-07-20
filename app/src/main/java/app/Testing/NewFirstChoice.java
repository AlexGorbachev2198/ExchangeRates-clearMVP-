package app.Testing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.red_ragnar.testing.IView;
import com.example.red_ragnar.testing.Presenter;
import com.example.red_ragnar.testing.R;

import java.util.Map;

import static ru.bpc.mobilebanksdk.R.drawable.ic_currency_eur;
import static ru.bpc.mobilebanksdk.R.drawable.ic_currency_rub;
import static ru.bpc.mobilebanksdk.R.drawable.ic_currency_usd;

public class NewFirstChoice extends AppCompatActivity implements IView {
    ImageButton _usdFrom;
    ImageButton _usdTo;
    TextView _text;
    Presenter _prsnt;
    String _from = "USD", _to = "USD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_first_choice);

        _usdFrom = (ImageButton) findViewById(R.id.FromButton);
        _usdTo = (ImageButton) findViewById(R.id.ToButton);
        _text = (TextView) findViewById(R.id.Rates_Info);
        _text.setText("");
        _prsnt = new Presenter(this);

        _usdFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _prsnt.FromButtonClick();
            }
        });
        _usdTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _prsnt.ToButtonClick();
            }
        });
    }

    public void ChangePicFromButton(String rate) {
        switch (rate) {
            case "USD":
                _usdFrom.setImageResource(ic_currency_usd);
                break;
            case "RUR":
                _usdFrom.setImageResource(ic_currency_rub);
                break;
            case "EUR":
                _usdFrom.setImageResource(ic_currency_eur);
        }
    }

    public void ChangePicToButton(String rate) {
        switch (rate) {
            case "USD":
                _usdTo.setImageResource(ic_currency_usd);
                break;
            case "RUR":
                _usdTo.setImageResource(ic_currency_rub);
                break;
            case "EUR":
                _usdTo.setImageResource(ic_currency_eur);
        }
    }

    public void SetText(String data) {
        _text.setText(data);
    }

    public String GetUsdFrom() {
        return _from;
    }

    public String GetUsdTo() {
        return _to;
    }

    public void SetUsdFrom(String rate) {
        _from = rate;
    }

    public void SetUsdTo(String rate) {
        _to = rate;
    }
}
