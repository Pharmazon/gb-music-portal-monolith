package ru.geekbrains.musicportal.entity.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Data
@Embeddable
public class UserMembership {

    @Column(name = "is_approved")
    private boolean isApproved;

    @Column(name = "is_locked_out")
    private boolean isLockedOut;

    @Column(name = "create_date")
//    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name = "last_login_date")
//    @CreationTimestamp
    private LocalDateTime lastLoginDate;

    @Column(name = "last_lockout_date")
//    @CreationTimestamp
    private LocalDateTime lastLockoutDate;

    @Column(name = "last_password_change_date")
//    @CreationTimestamp
    private LocalDateTime lastPasswordChangeDate;

    @Column(name = "failed_password_attempt_count")
    private int failedPasswordAttemptCount;

    @Column(name = "failed_password_attempt_window_start")
//    @CreationTimestamp
    private LocalDateTime failedPasswordAttemptWindowStart;

    @Column(name = "failed_password_answer_attempt_count")
    private int failedPasswordAnswerAttemptCount;

    @Column(name = "failed_password_answer_attempt_window_start")
//    @CreationTimestamp
    private LocalDateTime failedPasswordAnswerAttemptWindowStart;

    @Column(name = "comment")
    private String comment;
}
