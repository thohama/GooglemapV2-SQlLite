package info.androidhive.googlemapsv2;

import java.util.ArrayList; 

import android.app.Activity; 
import android.app.Fragment;
import android.os.Bundle; 
import android.util.Log; 
import android.view.LayoutInflater;
import android.view.View; 
import android.view.ViewGroup;
import android.widget.Button; 
import android.widget.EditText; 
import android.widget.TableLayout; 
import android.widget.TableRow; 
import android.widget.TextView; 
import android.widget.Toast;
import android.content.Context;
import android.widget.*;
 
public class insert extends Activity { 
public DatabaseManager dm; 
EditText nama, hobi, edlat, edlong; 
Button addBtn; 
TableLayout tabel4data;// tabel for data 
 

/* 
public insert(){
	  dm = new DatabaseManager(this); 
      setupView(); 
      fungsiBtn(); 
      updateTable();
  }
  
  */
	
	@Override
 public void onCreate(Bundle savedInstanceState) { 
  super.onCreate(savedInstanceState); 
  setContentView(R.layout.insert);
 
  dm = new DatabaseManager(this); 
      setupView(); 
      fungsiBtn(); 
      updateTable(); 
}  
 
public void setupView() { 
  tabel4data = (TableLayout) findViewById(R.id.tabel_data); 
  nama = (EditText) findViewById(R.id.inNama); 
  hobi = (EditText) findViewById(R.id.inHobi); 
  edlat = (EditText) findViewById(R.id.inUpdateLat);
  edlong = (EditText) findViewById(R.id.inUpdateLong);
  //updateNama = (EditText) findViewById(R.id.inUpdateNama); 
  //updateHobi = (EditText) findViewById(R.id.inUpdateHobi); 
  //GetId = (EditText) findViewById(R.id.inGetId); 
  //idDel=(EditText)findViewById(R.id.idDelete); 
     
  addBtn = (Button) findViewById(R.id.btnAdd); 
  //getIdBtn = (Button) findViewById(R.id.btnGetId); 
  //updateBtn = (Button) findViewById(R.id.btnUpdate); 
  //delBtn = (Button) findViewById(R.id.btnDel); 
} 
 
  public void fungsiBtn() { 
  addBtn.setOnClickListener(new View.OnClickListener() { 
      @Override 
    public void onClick(View v) { 
      simpKamuta(); 
      kosongkanField(); 
    } 
    }); 
  
  /*
  getIdBtn.setOnClickListener(new View.OnClickListener() { 
      @Override 
    public void onClick(View b) { 
      ambilBaris(); 
    } 
    });

  updateBtn.setOnClickListener(new View.OnClickListener() { 
      @Override 
    public void onClick(View c) { 
      updateBaris(); 
      kosongkanField(); 
    } 
    }); 
  delBtn.setOnClickListener(new View.OnClickListener() { 
    @Override 
    public void onClick(View d) { 
        // TODO Auto-generated method stub 
      deleteData(); 
      kosongkanField(); 
    } 
    }); */
  } 
  
  protected void kosongkanField(){ 
    nama.setText(""); 
    hobi.setText("");
    edlat.setText("");
    edlong.setText("");
   // updateNama.setText(""); 
    //updateHobi.setText(""); 
    //GetId.setText(""); 
   // idDel.setText(""); 
  } 
  
  /*
 
  private void deleteData(){ 
  dm.deleteBaris(Long.parseLong(idDel.getText().toString())); 
  updateTable(); 
  } 
 
  protected void updateBaris() { 
  dm.updateBaris(Long.parseLong(GetId.getText().toString()),  
     updateNama.getText().toString(),  
      updateHobi.getText().toString()); 
  updateTable(); 
  } 
 */
  
  /*
  private void ambilBaris() { 
  try { 
  ArrayList<Object> baris; 
baris = 
dm.ambilBaris(Long.parseLong(GetId.getText().toString())); 
updateNama.setText((String) baris.get(1)); 
updateHobi.setText((String) baris.get(2)); 
} catch (NumberFormatException e) { 
e.printStackTrace(); 
Log.e("eror db", e.toString()); 
Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show(); 
  } 
} */
 
protected void simpKamuta() { 
try { 
dm.addRow(nama.getText().toString(), 
hobi.getText().toString(),edlat.getText().toString(), 
edlong.getText().toString()); 
updateTable(); 
} catch (Exception e) { 
e.printStackTrace(); 
Toast.makeText(getBaseContext(),"gagal simpan,"+ e.toString(),Toast.LENGTH_LONG).show(); 
  } 
} 
 
protected void updateTable() { 
	// menghapus data yang ada
while (tabel4data.getChildCount() > 1) { 
  tabel4data.removeViewAt(1); 
} 
ArrayList<ArrayList<Object>> data = dm.ambilSemuaBaris();// ambil data baris 


for (int posisi = 0; posisi < data.size(); posisi++) { // menampilkan data dari array SQL
  TableRow tabelBaris = new TableRow(this);   // TR
  ArrayList<Object> baris = data.get(posisi); 
 
  TextView idTxt = new TextView(this);   // TD
  idTxt.setText(baris.get(0).toString()); 
  tabelBaris.addView(idTxt); 
 
  TextView namaTxt = new TextView(this);  // TD
  namaTxt.setText(baris.get(1).toString()); 
  tabelBaris.addView(namaTxt); 
 
  TextView hobiTxt = new TextView(this);  // TD
  hobiTxt.setText(baris.get(2).toString()); 
  tabelBaris.addView(hobiTxt); 
  
  TextView latTxt = new TextView(this);  // TD
  latTxt.setText(baris.get(3).toString()); 
  tabelBaris.addView(latTxt);
  
  TextView longTxt = new TextView(this);  // TD
  longTxt.setText(baris.get(4).toString()); 
  tabelBaris.addView(longTxt);
 
  tabel4data.addView(tabelBaris); 
  } 
} 
} 