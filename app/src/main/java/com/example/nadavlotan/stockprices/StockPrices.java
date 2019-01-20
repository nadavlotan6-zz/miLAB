package com.example.nadavlotan.stockprices;

import android.app.Application;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class StockPrices {
    private Socket mSocket;
    private static final String URL = "http://10.0.2.2:8080";

    {
        try {
            mSocket = IO.socket(URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public Socket getmSocket() {
        return mSocket;
    }
}
