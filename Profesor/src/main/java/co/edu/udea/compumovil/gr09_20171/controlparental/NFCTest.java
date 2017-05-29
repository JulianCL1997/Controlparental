package co.edu.udea.compumovil.gr09_20171.controlparental;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class NFCTest extends AppCompatActivity {

    private NfcAdapter nfcAdapter;
    TextView textViewInfo, textViewTagInfo, textViewBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfctest);
       textViewBlock = (TextView) findViewById(R.id.block);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Toast.makeText(this,
                    "NFC NOT supported on this devices!",
                    Toast.LENGTH_LONG).show();
            finish();
        } else if (!nfcAdapter.isEnabled()) {
            Toast.makeText(this,
                    "NFC NOT Enabled!",
                    Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String action = intent.getAction();

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
            Toast.makeText(this,
                    "onResume() - ACTION_TAG_DISCOVERED",
                    Toast.LENGTH_SHORT).show();

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            if (tag == null) {
                textViewInfo.setText("tag == null");
            } else {

                readMifareClassic(tag);
            }
        } else {
            Toast.makeText(this,
                    "onResume() : " + action,
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void readMifareClassic(Tag tag) {
        MifareClassic mifareClassicTag = MifareClassic.get(tag);

         new ReadMifareClassicTask(mifareClassicTag).execute();
    }

    private class ReadMifareClassicTask extends AsyncTask<Void, Void, Void> {

        /*
        MIFARE Classic tags are divided into sectors, and each sector is sub-divided into blocks.
        Block size is always 16 bytes (BLOCK_SIZE). Sector size varies.
        MIFARE Classic 1k are 1024 bytes (SIZE_1K), with 16 sectors each of 4 blocks.
        */

        MifareClassic taskTag;
        int numOfBlock;
        boolean success;
        final int numOfSector = 32;
        final int numOfBlockInSector = 4;
        byte[][][] buffer = new byte[numOfSector][numOfBlockInSector][MifareClassic.BLOCK_SIZE];

        ReadMifareClassicTask(MifareClassic tag) {
            taskTag = tag;
            success = false;
        }

        @Override
        protected void onPreExecute() {
            textViewBlock.setText("Reading Tag, don't remove it!");
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                taskTag.connect();
               byte[] key= {(byte) 0xa0, (byte) 0xa1, (byte) 0xa2, (byte) 0xa3, (byte) 0xa4, (byte) 0xa5,(byte)0x78,
                       (byte)0x77, (byte) 0x88,(byte)0x00};
                for (int s = 18; s < 19; s++) {
                    if (taskTag.authenticateSectorWithKeyA(s,key)) {
                        for (int b = 0; b < 1; b++) {
                            int blockIndex = (s * numOfBlockInSector) + b;
                            byte[] bloque=taskTag.readBlock(blockIndex);

                            buffer[s][b] = bloque;
                        }
                    }
                }

                success = true;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (taskTag != null) {
                    try {
                        taskTag.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //display block
            if (success) {
                String stringBlock = "";
                for (int i = 0; i < numOfSector; i++) {
                    stringBlock += i + " :\n";
                    for (int j = 0; j < numOfBlockInSector; j++) {
                        for (int k = 0; k < MifareClassic.BLOCK_SIZE; k++) {
                            stringBlock += String.format("%02X", buffer[i][j][k] & 0xff) + " ";
                        }
                        stringBlock += "\n";
                    }
                    stringBlock += "\n";
                }
                textViewBlock.setText(stringBlock);
            } else {
                textViewBlock.setText("Fail to read Blocks!!!");
            }
        }
    }
}
