package com.bpc.modulesdk.activity.common;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.bpc.modulesdk.modulity.facilities.sessionFacility.SessionFacility;
import com.bpc.modulesdk.security.SharedPreferencesHelper;
import com.bpc.modulesdk.utils.ContextWrapper;

import java.util.Locale;

/**
 * Created by Samoylov on 27.01.2017.
 * <p>
 * Every Activity should implement it
 */
public abstract class CommonActivity extends AppCompatActivity {

    //protected SessionHolder sessionHolder = SessionManagerHolder.get().getSessionHolder(); //TODO вынести в отдельный компонент - вот сюда, панпример

    @Override
    protected void attachBaseContext(Context newBase) {
        //Accept application language settings
        String localeTag = SharedPreferencesHelper.getLocale();
        if (localeTag == null) {
            super.attachBaseContext(newBase);
            return;
        }

        Locale newLocale = new Locale(localeTag);
        newBase = ContextWrapper.wrap(newBase, newLocale);
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SessionFacility.isCloseSessionDeferred())
            SessionFacility.closeSession();
    }

    //TODO вынести в отдельный компонент - вот сюда, например
  /*  public void showLogoutDialog() {
        DialogHelper.showYesNoDialog(this, -1, R.string.logout_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doLogout();
                Intent intent = new Intent(ToolBarActivity.this, BaseApp.getApp().getSdkConfig().getLoginActivity());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                dialog.dismiss();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }


    private void doLogout() {
        WebService.getLogoutRequest()
                .onErrorReturn(new Func1<Throwable, MainResponse>() {
                    @Override
                    public MainResponse call(Throwable throwable) {
                        return null;
                    }
                })
                .subscribe();
        SessionHolder sessionHolder = SessionManagerHolder.get().getSessionHolder();
        sessionHolder.endSession();
        WebService.removeAllDataFromCache();
    }*/


    //TODO вынести в отдельный компонент - вот сюда, например
   /*
    @Override
    protected void onResume() {
        super.onResume();
        //sessionCheck();
    }

   private void sessionCheck() {
        if (sessionHolder.isAuthorized()) {
            WebService.getSessionCheckRequest().subscribe(new Action1<MainResponse>() {
                @Override
                public void call(MainResponse response) {
                    if (!response.isSuccess()) errorHandler.handle(response);
                }
            }, WebService.createErrorHandleAction(errorHandler));
        }
    }*/
}
