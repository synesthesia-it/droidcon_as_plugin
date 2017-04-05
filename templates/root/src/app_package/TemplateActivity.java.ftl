package ${packageName};

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;



public class ${activityName}Activity extends ${superClass} {

    private static final String TAG = ${activityName}Activity.class.getSimpleName();

    public static Intent getCallingIntent(Context context) {
        Intent i =  new Intent(context, ${activityName}Activity.class);
        return i;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_${activityName?lower_case});
    }

}
