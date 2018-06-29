package com.example.pc.campusapplication;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddEventActivity extends AppCompatActivity {
    TextView tvCalendar;
    TextView tvTime;
    TextView tvImage;
    EditText edtName;
    Calendar calendar = Calendar.getInstance();
    int currentHour;
    int currentMinute;
    String amPm;
    App app;
    ImageView ivPreview;
    public static final int TAKE_PICTURE_REQUEST_CODE = 1001;
    public final static int PICK_PHOTO_REQUEST_CODE = 1002;
    public String photoFileName = "photo.jpg";
    File photoFile;
    DatabaseReference db;
    StorageReference storageRef;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        tvCalendar = findViewById(R.id.txtNewEventDate);
        tvTime = findViewById(R.id.txtNewEventTime);
        edtName = findViewById(R.id.txtNewEventName);
        tvImage = findViewById(R.id.txtNewEventImage);
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
        tvImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
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
            Bitmap selectedImage = null;
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Load the selected image into a preview
            ivPreview = (ImageView) findViewById(R.id.ivNewEventImage);
            ivPreview.setImageBitmap(selectedImage);
        }
    }


    //    private void writeUserData(String uid, String email) {
//        String firstName = txtFirst.getText().toString().trim();
//        String lastName = txtLast.getText().toString().trim();
//        String token = app.loadPref(App.FCM_TOKEN);
//        User user = new User(uid, email, firstName, lastName, token);
//        db.child("users").child(uid).setValue(user);
//        finish(); // back to sign in activity
//        // go to main activity
//    }

    private void addEventToDB(String uid){
        String eventName = edtName.getText().toString().trim();
        String eventDate = tvCalendar.getText().toString().trim();
        String eventTime = tvTime.getText().toString().trim();
        String token = app.loadPref(App.FCM_TOKEN);
        int eventThumbnail = ivPreview.
        Event event = new Event(eventName,eventDate,"",eventTime,)
    }
}
