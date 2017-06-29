package com.codepath.apps.restclienttemplate.models;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    public static String TWITTER_KEY; // Change this, base API URL
    public static String USER_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
    }

    public void onSubmit(View v) {
        EditText etName = (EditText) findViewById(R.id.et_composeTweet);
        final String message = etName.getText().toString();
        TwitterClient twitterClient = new TwitterClient(this);
        twitterClient.sendTweet(message, new JsonHttpResponseHandler(){

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Intent data = new Intent();

                    Tweet tweet = Tweet.fromJSON(response);
                    data.putExtra("tweet", tweet);
                    data.putExtra("someName", "someBody");
                    //data.putExtra(USER_KEY, tweet.user);
                    // data.putExtra(USER_KEY, tweet.user);
                    setResult(RESULT_OK, data); // set result code and bundle data for response

                    finish();
                } catch (JSONException e){
                    e.printStackTrace();
                }

                // closes the activity, pass data to parent

            }

        });

    }

    public void onEditTweet(View view) {
        EditText etName = (EditText) findViewById(R.id.et_composeTweet);
        etName.setText("", null);
    }
}
