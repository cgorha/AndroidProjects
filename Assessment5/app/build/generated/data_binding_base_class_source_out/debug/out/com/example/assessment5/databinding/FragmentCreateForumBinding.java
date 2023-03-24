// Generated by view binder compiler. Do not edit!
package com.example.assessment5.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.assessment5.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentCreateForumBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonCancel;

  @NonNull
  public final Button buttonSubmit;

  @NonNull
  public final EditText editTextForumTitle;

  private FragmentCreateForumBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button buttonCancel, @NonNull Button buttonSubmit,
      @NonNull EditText editTextForumTitle) {
    this.rootView = rootView;
    this.buttonCancel = buttonCancel;
    this.buttonSubmit = buttonSubmit;
    this.editTextForumTitle = editTextForumTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentCreateForumBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentCreateForumBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_create_forum, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentCreateForumBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonCancel;
      Button buttonCancel = ViewBindings.findChildViewById(rootView, id);
      if (buttonCancel == null) {
        break missingId;
      }

      id = R.id.buttonSubmit;
      Button buttonSubmit = ViewBindings.findChildViewById(rootView, id);
      if (buttonSubmit == null) {
        break missingId;
      }

      id = R.id.editTextForumTitle;
      EditText editTextForumTitle = ViewBindings.findChildViewById(rootView, id);
      if (editTextForumTitle == null) {
        break missingId;
      }

      return new FragmentCreateForumBinding((ConstraintLayout) rootView, buttonCancel, buttonSubmit,
          editTextForumTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
