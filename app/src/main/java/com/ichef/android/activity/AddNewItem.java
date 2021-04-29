package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ichef.android.R;
import com.ichef.android.utils.CommonUtility;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AddNewItem extends AppCompatActivity {
    ImageView upload_file1,upload_file2,upload_file3,upload_file4;
    String imgClick ="";
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap1,bitmap2,bitmap3,bitmap4;
    RadioButton rd1,rd2,rd3;
    TextView sun,mon,tue,wed,thu,fri,sat;
    String ssun="0",smon="0",stue="0",swed="0",sthu="0",sfri="0",ssat="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
        onlclick();
    }
    private void init() {
        rd1 = findViewById(R.id.rd1);
        rd2 = findViewById(R.id.rd2);
        rd3 = findViewById(R.id.rd3);

        sun = findViewById(R.id.sun);
        mon = findViewById(R.id.mon);
        tue = findViewById(R.id.tue);
        wed = findViewById(R.id.wed);
        thu = findViewById(R.id.thu);
        fri = findViewById(R.id.fri);
        sat = findViewById(R.id.sat);


        upload_file1=(ImageView) findViewById(R.id.upload1);
        upload_file2=(ImageView) findViewById(R.id.upload2);
        upload_file3=(ImageView) findViewById(R.id.upload3);
        upload_file4=(ImageView) findViewById(R.id.upload4);
    }

    private void onlclick() {
        rd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd1.setChecked(true);
                rd2.setChecked(false);
                rd3.setChecked(false);
            }
        });
        rd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd1.setChecked(false);
                rd2.setChecked(true);
                rd3.setChecked(false);
            }
        });
        rd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd1.setChecked(false);
                rd2.setChecked(false);
                rd3.setChecked(true);
            }
        });
        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ssun.equals("1")){
                    sun.setBackgroundResource(R.drawable.circle);
                    ssun="0";
                }
                else{
                sun.setBackgroundResource(R.drawable.circle2);
                ssun="1";}
            }
        });
        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (smon.equals("1")){
                    mon.setBackgroundResource(R.drawable.circle);
                    smon="0";
                }
                else{
                mon.setBackgroundResource(R.drawable.circle2);
                smon="1";}
            }
        });
        tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stue.equals("1")){
                    tue.setBackgroundResource(R.drawable.circle);
                    stue="0";
                }
                else{
                tue.setBackgroundResource(R.drawable.circle2);
                stue="1";}
            }
        });
        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swed.equals("1")){
                    wed.setBackgroundResource(R.drawable.circle);
                    swed="0";
                }
                else{
                wed.setBackgroundResource(R.drawable.circle2);
                swed="1";}
            }
        });
        thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sthu.equals("1")){
                    thu.setBackgroundResource(R.drawable.circle);
                    sthu="0";
                }
                else{
                thu.setBackgroundResource(R.drawable.circle2);
                sthu="1";}
            }
        });
        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sfri.equals("1")){
                    fri.setBackgroundResource(R.drawable.circle);
                    sfri="0";
                }
                else{
                fri.setBackgroundResource(R.drawable.circle2);
                sfri="1";}
            }
        });
        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ssat.equals("1")){
                    sat.setBackgroundResource(R.drawable.circle);
                    ssat="0";
                }
                else{
                sat.setBackgroundResource(R.drawable.circle2);
                ssat="1";}
            }
        });


        upload_file1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgClick ="1";
                selectImage();
                //  showFileChooser();
            }
        });
        upload_file2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgClick ="2";
                selectImage();
                //  showFileChooser();
            }
        });
        upload_file3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgClick ="3";
                selectImage();
                //  showFileChooser();
            }
        });
        upload_file4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgClick ="4";
                selectImage();
                //  showFileChooser();
            }
        });

    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, (dialog, item) -> {
            //    boolean result = CommonUtility.checkPermission(SignUpActivity.this);

            boolean b= CommonUtility.checkAndRequestPermissions(this);

            //  boolean b=CommonUtility.checkPermissionCamera(SignUpActivity.this);


            if (items[item].equals("Take Photo")) {
                userChoosenTask ="Take Photo";
                if(b)
                    cameraIntent();
                else
                    CommonUtility.checkAndRequestPermissions(this);



                //   requestPermission();

            } else if (items[item].equals("Choose from Library")) {
                userChoosenTask ="Choose from Library";
                if(b)
                    showFileChooser();

            } else if (items[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            Uri picUri = data.getData();

            try {
                try {
                    if (imgClick.equalsIgnoreCase("1")){
                        bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), picUri);
                        upload_file1.setImageBitmap(bitmap1);
                        imgClick ="";
                    }
                    else if (imgClick.equalsIgnoreCase("2")){
                        bitmap2 = MediaStore.Images.Media.getBitmap(getContentResolver(), picUri);
                        upload_file2.setImageBitmap(bitmap2);
                        imgClick ="";
                    }
                    else if (imgClick.equalsIgnoreCase("3")){
                        bitmap3 = MediaStore.Images.Media.getBitmap(getContentResolver(), picUri);
                        upload_file3.setImageBitmap(bitmap3);
                        imgClick ="";
                    }
                    else if (imgClick.equalsIgnoreCase("4")){
                        bitmap4 = MediaStore.Images.Media.getBitmap(getContentResolver(), picUri);
                        upload_file4.setImageBitmap(bitmap4);
                        imgClick ="";
                    }
                     } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if (requestCode == REQUEST_CAMERA && null !=data){
           /* bitmap = (Bitmap) data.getExtras().get("data");
            upload_file1.setImageBitmap(bitmap);*/

            if (imgClick.equalsIgnoreCase("1")){
                bitmap1 = (Bitmap) data.getExtras().get("data");
                upload_file1.setImageBitmap(bitmap1);
                imgClick ="";

            }
            else if (imgClick.equalsIgnoreCase("2")){
                bitmap2 = (Bitmap) data.getExtras().get("data");
                upload_file2.setImageBitmap(bitmap2);
                imgClick ="";

            }
            else if (imgClick.equalsIgnoreCase("3")){
                bitmap3 = (Bitmap) data.getExtras().get("data");
                upload_file3.setImageBitmap(bitmap3);
                imgClick ="";
            }
            else if (imgClick.equalsIgnoreCase("4")){
                bitmap4 = (Bitmap) data.getExtras().get("data");
                upload_file4.setImageBitmap(bitmap4);
                imgClick ="";
            }


          /*  ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);*/
            //  byte[] byteArray = byteArrayOutputStream .toByteArray();
        }
    }
    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent, REQUEST_CAMERA);
    }


}