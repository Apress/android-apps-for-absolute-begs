package Intent.Filters;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class IntentExamples extends Activity {   
    @Override											/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        
        Button Activity1 = (Button) findViewById(R.id.Button01);
        Activity1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), DigitalClockActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startService(new Intent(getBaseContext(), MediaPlayerService.class));
            }
        });
        Button stopButton = (Button) findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                stopService(new Intent(getBaseContext(), MediaPlayerService.class));
            }
        });        
    }
}