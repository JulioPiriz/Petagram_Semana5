package uy.drako.petagram;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ContactoActivity extends AppCompatActivity {
    private String email,password, para;
    EditText from;
    EditText msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        para ="tu ail aca";
        from =findViewById(R.id.from);
        msg  =findViewById(R.id.msg);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public void onClickBtn(View v) {
        email="mail@gmail.com";
        password="tu contraseña aca";
        //String para = para.getEditText().getText().toString();

        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        javax.mail.Session session= Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email,password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(para.toString().trim()));
            message.setSubject(from.getText().toString().trim());
            message.setText(msg.getText().toString().trim());
            new SendMail().execute(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Gracias por comunicarte con nosotros", Toast.LENGTH_LONG).show();
        this.finish();
    }

    private class SendMail extends AsyncTask<Message,String,String> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=ProgressDialog.show(ContactoActivity.this,"Por favor espere","Enviando mail...",true,false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "exito";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Error";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if(s.equals("exito")){
                AlertDialog.Builder builder= new AlertDialog.Builder(ContactoActivity.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324'>Exito</font>"));
                builder.setMessage("El mail fue enviado con éxito");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                       // para.setText("");
                        from.setText("");
                        msg.setText("");

                    }
                });
                builder.show();
            }else{
                Toast.makeText(ContactoActivity.this,"El password o el mail son incorrectos",Toast.LENGTH_LONG).show();
            }
        }
    }

}