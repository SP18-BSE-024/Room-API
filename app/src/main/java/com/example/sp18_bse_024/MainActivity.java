package com.example.sp18_bse_024;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private Userdatabase udb;
    EditText name,password;
    TextView id;
    Button add,search,delete,clear,update,deleteall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        udb = Room.databaseBuilder(getApplicationContext(), Userdatabase.class, "app-db").build();

        id=findViewById(R.id.id);
        name=findViewById(R.id.Name);
        password=findViewById(R.id.Password);
        add=findViewById(R.id.Add);
        search=findViewById(R.id.Search);
        delete=findViewById(R.id.Delete);
        clear=findViewById(R.id.Clear);
        update=findViewById(R.id.Udpate);
        deleteall=findViewById(R.id.deleteall);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExecutorService executorService = Executors.newFixedThreadPool(1);
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        User user = new User();
                        user.UserName = name.getText().toString();
                        user.Password = password.getText().toString();

                        UserDao userDao = udb.userDao();

                        try {

                            userDao.insertOne(user);

                            view.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "User Added!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (SQLiteConstraintException e) {
                            Toast.makeText(MainActivity.this, "Something went wrong ..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });





        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExecutorService executorService = Executors.newFixedThreadPool(1);
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        UserDao userDao = udb.userDao();
                        User user = userDao.findByName(name.getText().toString());
                        if(user != null) {
                            view.post(new Runnable() {
                                @Override
                                public void run() {
                                    String i= String.valueOf(user.ID);
                                    id.setText(i);
                                    name.setText(user.UserName);
                                    password.setText(user.Password);


                                }
                            });
                        } else {
                            view.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "User not found!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });

            }
        });





        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExecutorService executorService = Executors.newFixedThreadPool(1);
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        UserDao userDao = udb.userDao();

                        User user = new User();
                        user.UserName = name.getText().toString();

                        userDao.del(user.UserName);

                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "User Deleted!", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                });

            }
        });




        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id.setText("");
                name.setText("");
                password.setText("");
            }
        });


        deleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExecutorService executorService = Executors.newFixedThreadPool(1);
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        UserDao userDao = udb.userDao();

                        userDao.DeleteAll();

                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "All Users Deleted!", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                });

            }
        });

    }

}