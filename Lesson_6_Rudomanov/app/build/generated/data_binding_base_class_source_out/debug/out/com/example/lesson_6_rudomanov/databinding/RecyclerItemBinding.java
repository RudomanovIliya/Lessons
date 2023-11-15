// Generated by view binder compiler. Do not edit!
package com.example.lesson_6_rudomanov.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.lesson_6_rudomanov.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class RecyclerItemBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final EditText editTextNewInfo;

  @NonNull
  public final EditText editTextNewInfoThree;

  @NonNull
  public final EditText editTextNewInfoTwo;

  @NonNull
  public final ImageView imageViewAlert;

  @NonNull
  public final ImageView imageViewInfo;

  @NonNull
  public final ImageView imageViewItem;

  @NonNull
  public final ImageView imageViewMore;

  @NonNull
  public final ImageView imageViewSend;

  @NonNull
  public final TextView textViewAlert;

  @NonNull
  public final TextView textViewNewInfo;

  @NonNull
  public final TextView textViewNewInfoThree;

  @NonNull
  public final TextView textViewNewInfoTwo;

  @NonNull
  public final TextView textViewSerialNumber;

  @NonNull
  public final TextView textViewTitle;

  private RecyclerItemBinding(@NonNull CardView rootView, @NonNull EditText editTextNewInfo,
      @NonNull EditText editTextNewInfoThree, @NonNull EditText editTextNewInfoTwo,
      @NonNull ImageView imageViewAlert, @NonNull ImageView imageViewInfo,
      @NonNull ImageView imageViewItem, @NonNull ImageView imageViewMore,
      @NonNull ImageView imageViewSend, @NonNull TextView textViewAlert,
      @NonNull TextView textViewNewInfo, @NonNull TextView textViewNewInfoThree,
      @NonNull TextView textViewNewInfoTwo, @NonNull TextView textViewSerialNumber,
      @NonNull TextView textViewTitle) {
    this.rootView = rootView;
    this.editTextNewInfo = editTextNewInfo;
    this.editTextNewInfoThree = editTextNewInfoThree;
    this.editTextNewInfoTwo = editTextNewInfoTwo;
    this.imageViewAlert = imageViewAlert;
    this.imageViewInfo = imageViewInfo;
    this.imageViewItem = imageViewItem;
    this.imageViewMore = imageViewMore;
    this.imageViewSend = imageViewSend;
    this.textViewAlert = textViewAlert;
    this.textViewNewInfo = textViewNewInfo;
    this.textViewNewInfoThree = textViewNewInfoThree;
    this.textViewNewInfoTwo = textViewNewInfoTwo;
    this.textViewSerialNumber = textViewSerialNumber;
    this.textViewTitle = textViewTitle;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static RecyclerItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static RecyclerItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.recycler_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static RecyclerItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.editTextNewInfo;
      EditText editTextNewInfo = ViewBindings.findChildViewById(rootView, id);
      if (editTextNewInfo == null) {
        break missingId;
      }

      id = R.id.editTextNewInfoThree;
      EditText editTextNewInfoThree = ViewBindings.findChildViewById(rootView, id);
      if (editTextNewInfoThree == null) {
        break missingId;
      }

      id = R.id.editTextNewInfoTwo;
      EditText editTextNewInfoTwo = ViewBindings.findChildViewById(rootView, id);
      if (editTextNewInfoTwo == null) {
        break missingId;
      }

      id = R.id.imageViewAlert;
      ImageView imageViewAlert = ViewBindings.findChildViewById(rootView, id);
      if (imageViewAlert == null) {
        break missingId;
      }

      id = R.id.imageViewInfo;
      ImageView imageViewInfo = ViewBindings.findChildViewById(rootView, id);
      if (imageViewInfo == null) {
        break missingId;
      }

      id = R.id.imageViewItem;
      ImageView imageViewItem = ViewBindings.findChildViewById(rootView, id);
      if (imageViewItem == null) {
        break missingId;
      }

      id = R.id.imageViewMore;
      ImageView imageViewMore = ViewBindings.findChildViewById(rootView, id);
      if (imageViewMore == null) {
        break missingId;
      }

      id = R.id.imageViewSend;
      ImageView imageViewSend = ViewBindings.findChildViewById(rootView, id);
      if (imageViewSend == null) {
        break missingId;
      }

      id = R.id.textViewAlert;
      TextView textViewAlert = ViewBindings.findChildViewById(rootView, id);
      if (textViewAlert == null) {
        break missingId;
      }

      id = R.id.textViewNewInfo;
      TextView textViewNewInfo = ViewBindings.findChildViewById(rootView, id);
      if (textViewNewInfo == null) {
        break missingId;
      }

      id = R.id.textViewNewInfoThree;
      TextView textViewNewInfoThree = ViewBindings.findChildViewById(rootView, id);
      if (textViewNewInfoThree == null) {
        break missingId;
      }

      id = R.id.textViewNewInfoTwo;
      TextView textViewNewInfoTwo = ViewBindings.findChildViewById(rootView, id);
      if (textViewNewInfoTwo == null) {
        break missingId;
      }

      id = R.id.textViewSerialNumber;
      TextView textViewSerialNumber = ViewBindings.findChildViewById(rootView, id);
      if (textViewSerialNumber == null) {
        break missingId;
      }

      id = R.id.textViewTitle;
      TextView textViewTitle = ViewBindings.findChildViewById(rootView, id);
      if (textViewTitle == null) {
        break missingId;
      }

      return new RecyclerItemBinding((CardView) rootView, editTextNewInfo, editTextNewInfoThree,
          editTextNewInfoTwo, imageViewAlert, imageViewInfo, imageViewItem, imageViewMore,
          imageViewSend, textViewAlert, textViewNewInfo, textViewNewInfoThree, textViewNewInfoTwo,
          textViewSerialNumber, textViewTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
