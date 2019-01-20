package com.example.nadavlotan.stockprices;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {

    private String stock;
    JSONObject params = new JSONObject();
    Socket mSocket;

    TextView priceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StockPrices stockPrices = new StockPrices();
        mSocket = stockPrices.getmSocket();
        mSocket.on("message", onMessage);
        mSocket.connect();

        // Get reference of widgets from XML layout
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.spinnerItems, android.R.layout.simple_spinner_item);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

        Button infoButton = (Button) findViewById(R.id.getInfoButton);
        final TextView stockTextView = (TextView) findViewById(R.id.stockPriceTextView);
        priceTextView = (TextView) findViewById(R.id.priceTextView);

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stock = spinner.getSelectedItem().toString();
                try {
                    params.put("stock", stock);
                    stockTextView.setVisibility(View.VISIBLE);
                    stockTextView.setText(stock);
                    Toast.makeText(MainActivity.this, "The desired price will be presented soon", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
                mSocket.emit("message", params);
            }
        });
    }

    private Emitter.Listener onMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String stockPrice = (String) args[0];
                    priceTextView.setVisibility(View.VISIBLE);
                    priceTextView.setText(stockPrice);
                    Toast.makeText(MainActivity.this, stockPrice, Toast.LENGTH_LONG).show();
                }
            });
        }
    };
}
