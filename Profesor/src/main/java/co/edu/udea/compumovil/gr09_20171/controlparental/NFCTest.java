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
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

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
                MifareClassic mifareClassicTag = MifareClassic.get(tag);
                new ReadMifareClassicTask(mifareClassicTag).execute();
            }
        } else {
            Toast.makeText(this,
                    "onResume() : " + action,
                    Toast.LENGTH_SHORT).show();
        }

    }


    private class ReadMifareClassicTask extends AsyncTask<Void, Void, Void> {

        /*
        MIFARE Classic tags are divided into sectors, and each sector is sub-divided into blocks.
        Block size is always 16 bytes (BLOCK_SIZE). Sector size varies.
        MIFARE Classic 1k are 1024 bytes (SIZE_1K), with 16 sectors each of 4 blocks.
        */

        MifareClassic taskTag;
        boolean success;
        final int SECTOR_READ = 18;
        final int BLOCK_READ = 72;
        byte[] buffer = new byte[MifareClassic.BLOCK_SIZE];

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
                byte[] key = {(byte) 0xa0, (byte) 0xa1, (byte) 0xa2, (byte) 0xa3, (byte) 0xa4, (byte) 0xa5, (byte) 0x78,
                        (byte) 0x77, (byte) 0x88, (byte) 0x00};
                if (taskTag.authenticateSectorWithKeyA(SECTOR_READ, key)) {
                    buffer = taskTag.readBlock(BLOCK_READ);

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
                String stringBlock = null;
                try {
                    stringBlock = new String(buffer, "UTF-8");
                    stringBlock = stringBlock.substring(5, stringBlock.length() - 1);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                //stringBlock += String.format("%02X", buffer[k] & 0xff) + " ";

                textViewBlock.setText(stringBlock);
            } else {
                textViewBlock.setText("Fail to read Blocks!!!");
            }
        }
    }
}
