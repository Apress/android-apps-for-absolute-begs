package event.handling;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
import android.widget.Toast;

public class HandlerExamples extends Activity {	
    @Override			/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button secondButton = (Button) findViewById(R.id.secondButton);
        registerForContextMenu(secondButton);
        Button thirdButton = (Button) findViewById(R.id.thirdButton);
        registerForContextMenu(thirdButton);
        Button contextButton = (Button) findViewById(R.id.contextButton);
        registerForContextMenu(contextButton);        
    }
    @Override			/** Override Parent Class for this Application */
    public void onCreateContextMenu(ContextMenu menu, View view,ContextMenuInfo menuInfo) {
	super.onCreateContextMenu(menu, view, menuInfo);
		menu.setHeaderTitle("Android Context Menu");
		menu.add(0, view.getId(), 0, "Invoke Context Function 1");
		menu.add(0, view.getId(), 0, "Invoke Context Function 2");
	}
    @Override
	public boolean onContextItemSelected(MenuItem item) {
       	if(item.getTitle()=="Invoke Context Function 1"){contextFunction1(item.getItemId());}
    	else if(item.getTitle()=="Invoke Context Function 2"){contextFunction2(item.getItemId());}
    	else {return false;}
	return true;
	}
    public void contextFunction1(int id){
    	Toast.makeText(this, "function 1 invoked!", Toast.LENGTH_SHORT).show();
    }
    public void contextFunction2(int id){
    	Toast.makeText(this, "function 2 invoked!", Toast.LENGTH_SHORT).show();
    }        
	public boolean onKeyDown(int keyCode, KeyEvent event) {				
		if (keyCode == KeyEvent.KEYCODE_ENTER) {		
					textUpdate();
		            return true;
		    }
			return false;
		}	
	public void textUpdate() {
		TextView text = (TextView)findViewById(R.id.testText);
		text.setText("ENTER KEY PRESSED!");		
	}
}