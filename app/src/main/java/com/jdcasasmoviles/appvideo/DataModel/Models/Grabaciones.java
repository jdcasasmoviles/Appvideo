package com.jdcasasmoviles.appvideo.DataModel.Models;
import com.google.gson.annotations.SerializedName;

public class Grabaciones {
 @SerializedName("profile")
 private Profile profile;
 @SerializedName("description")
 private String descripcion;
 @SerializedName("record_video")
 private String record_video;
 @SerializedName("preview_img")
 private String preview_img;

 public Grabaciones(Profile profile,String descripcion,String record_video,String preview_img){
  this.profile=profile;
  this.descripcion=descripcion;
  this.record_video=record_video;
  this.preview_img=preview_img;
 }

 public String getDescripcion() {
  return descripcion;
 }

 public void setDescripcion(String descripcion) {
  this.descripcion = descripcion;
 }

 public String getRecord_video() {
  return record_video;
 }

 public void setRecord_video(String record_video) {
  this.record_video = record_video;
 }

 public String getPreview_img() {
  return preview_img;
 }

 public void setPreview_img(String preview_img) {
  this.preview_img = preview_img;
 }

 public Profile getProfile() {
  return profile;
 }

 public void setProfile(Profile profile) {
  this.profile = profile;
 }
}
