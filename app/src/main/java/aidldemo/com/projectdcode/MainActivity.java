package aidldemo.com.projectdcode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_dddddmain)
public class MainActivity extends Activity{
    @ViewById(R.id.main_btn)
    public Button main_btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Click(R.id.main_btn)
    public void onClick(View v) {
        showThemed();
    }
    private void showThemed() {
        new MaterialDialog.Builder(this)
                .title(R.string.progress_dialog)
                .content(R.string.please_wait)
                .progress(true, 0)
                .show();
    }
}
