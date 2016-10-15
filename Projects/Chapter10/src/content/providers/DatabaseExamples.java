package content.providers;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.net.Uri;
import android.widget.Toast;
import android.database.Cursor;
import android.provider.Contacts.People;
import android.content.ContentValues;
public class DatabaseExamples extends Activity {
	public Uri addUri = null;
	public Uri changeUri = null; 	
    @Override	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button queryButton = (Button)findViewById(R.id.queryButton);
        queryButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v){
               queryContactPhoneNumber();
            }
        });        
        Button addButton = (Button)findViewById(R.id.addContactButton);
        addButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v){
               addContactPhoneNumber("Steve Wozniak", "415-555-7654");
            }
        });        
        Button modButton = (Button)findViewById(R.id.modifyPhoneButton);
        modButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v){
               modifyPhoneNumber("916-555-1234");
            }
        });        
        Button delButton = (Button)findViewById(R.id.deleteContactButton);
        delButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v){
               deleteContactPhoneNumber();
            }
        });        
    }    
	private void queryContactPhoneNumber() {
		      String[] cols = new String[] {People.NAME, People.NUMBER};
		      Uri myContacts = People.CONTENT_URI;
		      Cursor mqCur = managedQuery(myContacts,cols,null,null,null);
		      if (mqCur.moveToFirst()) {
		            String myname = null;
		            String mynumber = null;
		            do {
		              myname = mqCur.getString(mqCur.getColumnIndex(People.NAME));
		              mynumber = mqCur.getString(mqCur.getColumnIndex(People.NUMBER));
		              Toast.makeText(this, myname + " " + mynumber, Toast.LENGTH_SHORT).show();
		            } while (mqCur.moveToNext());
		      }
	}
	private void addContactPhoneNumber(String newName, String newPhone) {
    	ContentValues myContact = new ContentValues();
    	myContact.put(People.NAME, newName);
    	addUri = getContentResolver().insert(People.CONTENT_URI, myContact);
    	Uri contentUri = Uri.withAppendedPath(addUri, People.Phones.CONTENT_DIRECTORY);
    	myContact.clear();
    	myContact.put(People.Phones.TYPE, People.TYPE_MOBILE);
    	myContact.put(People.NUMBER, newPhone);
    	changeUri = getContentResolver().insert(contentUri, myContact);
    	Toast.makeText(this, "New Contact: " + newName + " " + newPhone, Toast.LENGTH_SHORT).show();
	}
	private void modifyPhoneNumber(String replacePhone) {
	      if (changeUri == null) {
	            Toast.makeText(this, "You need to create a new contact to update!", Toast.LENGTH_LONG).show();
	      } else {
	            ContentValues newPhoneNumber = new ContentValues();
	            newPhoneNumber.put(People.Phones.TYPE, People.TYPE_MOBILE);
	            newPhoneNumber.put(People.NUMBER, replacePhone);
	            getContentResolver().update(changeUri, newPhoneNumber, null,null);
	            Toast.makeText(this, "Updated phone number to: " + replacePhone, Toast.LENGTH_SHORT).show();
	      }
	    }
    private void deleteContactPhoneNumber() {
    	if (changeUri == null) {
    		Toast.makeText(this, "You need to create a new contact to delete!", Toast.LENGTH_LONG).show();
    		
    	} else {
    		getContentResolver().delete(addUri, null, null);
    		Toast.makeText(this, "Deleted contact at: " + addUri.toString(), Toast.LENGTH_SHORT).show();
    		addUri = null;
    		changeUri = null;
    	}
    }	
}        