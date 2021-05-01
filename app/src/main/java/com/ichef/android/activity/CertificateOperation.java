package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ichef.android.R;
import com.ichef.android.utils.CommonUtility;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CertificateOperation extends AppCompatActivity {
    TextView skip;
    RelativeLayout next;
    TextView passphot,license,certificate1,certificate2;
    String imgClick ="";
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap1,bitmap2,bitmap3,bitmap4;
    ImageView upload_file1,upload_file2,upload_file3,upload_file4;
    ImageView add1,add2,add3,add4,tik1,tik2,tik3,tik4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate_operation);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
        onclick();

    }

    private void onclick() {
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(CertificateOperation.this, R.anim.image_click));
                Intent intent = new Intent(CertificateOperation.this, AlmostDone.class);
                startActivity(intent);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(CertificateOperation.this, R.anim.image_click));
                Intent intent = new Intent(CertificateOperation.this, AlmostDone.class);
                startActivity(intent);
            }
        });
        passphot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgClick ="1";
                selectImage();
                //  showFileChooser();
            }
        });
        license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgClick ="2";
                selectImage();
                //  showFileChooser();
            }
        });

        certificate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgClick ="3";
                selectImage();
                //  showFileChooser();
            }
        });

        certificate2.setOnClickListener(new View.OnClickListener() {
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
                       // passphot.setText(requestCode);
                        upload_file1.setVisibility(View.VISIBLE);
                        upload_file1.setImageBitmap(bitmap1);
                        add1.setVisibility(View.GONE);
                        tik1.setVisibility(View.VISIBLE);
                        imgClick ="";
                    }
                    else if (imgClick.equalsIgnoreCase("2")){
                        bitmap2 = MediaStore.Images.Media.getBitmap(getContentResolver(), picUri);
                        upload_file2.setVisibility(View.VISIBLE);
                        upload_file2.setImageBitmap(bitmap2);
                        add2.setVisibility(View.GONE);
                        tik2.setVisibility(View.VISIBLE);

                        imgClick ="";
                    }
                    else if (imgClick.equalsIgnoreCase("3")){
                        bitmap3 = MediaStore.Images.Media.getBitmap(getContentResolver(), picUri);
                        upload_file3.setVisibility(View.VISIBLE);
                        upload_file3.setImageBitmap(bitmap3);
                        add3.setVisibility(View.GONE);
                        tik3.setVisibility(View.VISIBLE);

                        imgClick ="";
                    }
                    else if (imgClick.equalsIgnoreCase("4")){
                        bitmap4 = MediaStore.Images.Media.getBitmap(getContentResolver(), picUri);
                        upload_file4.setImageBitmap(bitmap4);
                        upload_file4.setVisibility(View.VISIBLE);
                        add4.setVisibility(View.GONE);
                        tik4.setVisibility(View.VISIBLE);
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
                upload_file1.setVisibility(View.VISIBLE);
                upload_file1.setImageBitmap(bitmap1);
                add1.setVisibility(View.GONE);
                tik1.setVisibility(View.VISIBLE);
                imgClick ="";

            }
            else if (imgClick.equalsIgnoreCase("2")){
                bitmap2 = (Bitmap) data.getExtras().get("data");
                upload_file2.setVisibility(View.VISIBLE);
                upload_file2.setImageBitmap(bitmap2);
                add2.setVisibility(View.GONE);
                tik2.setVisibility(View.VISIBLE);
                imgClick ="";

            }
            else if (imgClick.equalsIgnoreCase("3")){
                bitmap3 = (Bitmap) data.getExtras().get("data");
                upload_file3.setVisibility(View.VISIBLE);
                upload_file3.setImageBitmap(bitmap3);
                add3.setVisibility(View.GONE);
                tik3.setVisibility(View.VISIBLE);
                imgClick ="";
            }
            else if (imgClick.equalsIgnoreCase("4")){
                bitmap4 = (Bitmap) data.getExtras().get("data");
                upload_file4.setVisibility(View.VISIBLE);
                upload_file4.setImageBitmap(bitmap4);
                add4.setVisibility(View.GONE);
                tik4.setVisibility(View.VISIBLE);
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

    private void init() {
         skip = findViewById(R.id.skip);
         passphot = findViewById(R.id.passphoto);
         license = findViewById(R.id.licence);
         certificate1 = findViewById(R.id.certification);
         certificate2 = findViewById(R.id.certification2);
         next = findViewById(R.id.llcontinueco);

        upload_file1=(ImageView) findViewById(R.id.upload1);
        upload_file2=(ImageView) findViewById(R.id.upload2);
        upload_file3=(ImageView) findViewById(R.id.upload3);
        upload_file4=(ImageView) findViewById(R.id.upload4);

        add1=findViewById(R.id.add1);
        add2=findViewById(R.id.add2);
        add3=findViewById(R.id.add3);
        add4=findViewById(R.id.add4);
        tik1=findViewById(R.id.tik1);
        tik2=findViewById(R.id.tik2);
        tik3=findViewById(R.id.tik3);
        tik4=findViewById(R.id.tik4);
    }
}