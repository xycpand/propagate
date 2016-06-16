package com.hummingbird.propagate.util;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * <p>邮件实体</p>
 * @author panda
 */
public class MailTask
{
  private String mailAddress;
  private String subject;
  private String from;
  private String content;
  private List<File> attachments = new ArrayList<File>();
  private int againCount;

  public String getMailAddress()
  {
    return this.mailAddress;
  }

  public void setMailAddress(String mailAddress) {
    this.mailAddress = mailAddress;
  }

  public String getSubject() {
    return this.subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getFrom() {
    return this.from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public List<File> getAttachments() {
    return this.attachments;
  }

  public void setAttachments(List<File> attachments) {
    this.attachments = attachments;
  }

  public int getAgainCount() {
    return this.againCount;
  }

  public void setAgainCount(int againCount) {
    this.againCount = againCount;
  }
}