package com.hummingbird.propagate.util;


import java.io.File;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
/**
 * <p>发送邮件</p>
 * @author panda
 */
public class MailUtil
{
  protected LoggerUtil loggerUtil;
  private JavaMailSender mailSender;
  private static Queue<MailTask> mailTaskQueue = new ConcurrentLinkedQueue<MailTask>();

  public MailUtil()
  {
    this.loggerUtil = new LoggerUtil(MailUtil.class);
  }

  public void setMailSender(JavaMailSender mailSender)
  {
    this.mailSender = mailSender;
  }

  public void addMailTaskQueue(MailTask mailTask)
  {
    mailTaskQueue.add(mailTask);
  }

  public void init()
  {
    new Thread(new Send()).start();
    this.loggerUtil.info("发送邮件任务线程启动");
  }
  class Send implements Runnable {
    Send() {
    }
    public void run() {
      while (!Thread.interrupted())
      {
        MimeMessage mineMessage = MailUtil.this.mailSender.createMimeMessage();
        MailTask mailTask = null;
        int size = MailUtil.mailTaskQueue.size();
        if (size != 0) {
          MailUtil.this.loggerUtil.info("带发送邮件任务：" + MailUtil.mailTaskQueue.size() + "个");
        }

        try
        {
          for (int i = 0; i < MailUtil.mailTaskQueue.size(); i++) {
            mailTask = (MailTask)MailUtil.mailTaskQueue.remove();
            MailUtil.this.loggerUtil.info("发送邮件");

            if (mailTask.getAgainCount() < 5) {
              MimeMessageHelper messageHelper = new MimeMessageHelper(mineMessage, true, "utf-8");

              messageHelper.setTo(mailTask.getMailAddress());

              messageHelper.setFrom(mailTask.getFrom());

              messageHelper.setSubject(mailTask.getSubject());

              messageHelper.setText(mailTask.getContent(), true);

              if (mailTask.getAttachments() != null) {
                for (File file : mailTask.getAttachments()) {
                  messageHelper.addAttachment(MimeUtility.decodeText(file.getName()), file);
                }
              }
              MailUtil.this.loggerUtil.info("目的地[" + mailTask.getMailAddress() + "],从什么地方发出[" + mailTask.getFrom() + "],主题[" + mailTask.getSubject() + "]");
              MailUtil.this.mailSender.send(mineMessage);
            }
          }
        } catch (Exception e) {
          e.printStackTrace();

          MailUtil.mailTaskQueue.add(mailTask);
          int againCount = mailTask.getAgainCount();
          againCount++;
          mailTask.setAgainCount(againCount);
          MailUtil.this.loggerUtil.info("邮件发生异常，重新加入发送任务队列");
        }
        try {
          Thread.sleep(5000L);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}