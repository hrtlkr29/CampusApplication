package com.example.pc.campusapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

public class AddEventActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvCalendar;
    TextView tvTime;
    Button btnImage;
    EditText edtName, edtAddress;
    Button btnAddEvent;
    Calendar calendar = Calendar.getInstance();
    int currentHour;
    int currentMinute;
    String amPm;
    App app;
    ImageView ivPreview;
    Bitmap selectedImageBitmap;
    public static final int TAKE_PICTURE_REQUEST_CODE = 1001;
    public final static int PICK_PHOTO_REQUEST_CODE = 1002;
    public String photoFileName = "photo.jpg";
    File photoFile;
    DatabaseReference db;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    String ueid = UUID.randomUUID().toString().replaceAll("-", "");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        db = FirebaseDatabase.getInstance().getReference();
        tvCalendar = findViewById(R.id.txtNewEventDate);
        tvTime = findViewById(R.id.txtNewEventTime);
        edtName = findViewById(R.id.txtNewEventName);
        btnAddEvent = findViewById(R.id.btnAddEvent);
        btnImage = findViewById(R.id.btnAddImage);
        edtAddress = findViewById(R.id.txtNewEventAddress);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        tvCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddEventActivity.this,date,calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        tvTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        btnAddEvent.setOnClickListener(this);
    }

    private void updateLabel(){
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tvCalendar.setText(sdf.format(calendar.getTime()));
    }

    public void openGallery() {
        // Create intent for picking a photo from the gallery
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Bring up gallery to select a photo
            startActivityForResult(intent, PICK_PHOTO_REQUEST_CODE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == TAKE_PICTURE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                ivPreview = findViewById(R.id.ivNewEventImage);
                ivPreview.setImageBitmap(takenImage);
            } else {
                Toast.makeText(this, "No picture taken", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == PICK_PHOTO_REQUEST_CODE) {
            Uri photoUri = data.getData();
            // Do something with the photo based on Uri
            selectedImageBitmap = null;
            try {
                selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Load the selected image into a preview
            ivPreview = (ImageView) findViewById(R.id.ivNewEventImage);
            ivPreview.setImageBitmap(selectedImageBitmap);
        }
    }


    private void addEventToDB(){

        String eventName = edtName.getText().toString().trim();
        String eventDate = tvCalendar.getText().toString().trim();
        String eventTime = tvTime.getText().toString().trim();
        String dbReference = "images/" + eventName + ".jpg";
        String eventThumbnail = dbReference.replace(" ", "");
        String eventAddress = edtAddress.getText().toString().trim();
        Event event = new Event(ueid,eventName,eventDate,eventAddress,"",eventTime,eventThumbnail,"");
        db.child("events").child(ueid).setValue(event);
//        String name, String date, String address, String description, String time, String thumbnail
    }

    private void saveImageToDB(){
        String eventName = edtName.getText().toString().trim();
        eventName = eventName.replace(" ","");
        final StorageReference imageReference = storageRef.child("images/"+ueid);
        // Get the data from an ImageView as bytes
        ivPreview.setDrawingCacheEnabled(true);
        ivPreview.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) ivPreview.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = imageReference.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                StorageMetadata metadata = taskSnapshot.getMetadata();
                StorageReference imgReference = metadata.getReference();
                imgReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // Got the download URL for 'users/me/profile.png'
                        Log.d("Image uid",uri.toString());
                        db.child("events").child(ueid).child("imageUri").setValue(uri.toString());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });
            }
        });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnAddEvent){
            addEventToDB();
            saveImageToDB();
            finish();
        }
    }
}
