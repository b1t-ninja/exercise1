package com.example.exercise1;

import android.os.Bundle;

import java.io.PrintWriter;

public class MainActivity extends AbstractMessageSender {
    private PushClient.IOHandler mIOHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIOHandler = new PushClient.IOHandler() {

            private PrintWriter mPrintWriter;

            @Override
            public void displayInput(String input) {
                updateConversation(input);
            }

            @Override
            public void sendOutput(String output) {
                mPrintWriter.println(output);
            }

            @Override
            public void setOutputWriter(PrintWriter writer) {
                mPrintWriter = writer;
            }
        };

        new PushClient(mIOHandler).connect();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.conversation);
    }

    @Override
    protected void processMessage(String message) {
        mIOHandler.sendOutput(message);
    }
}