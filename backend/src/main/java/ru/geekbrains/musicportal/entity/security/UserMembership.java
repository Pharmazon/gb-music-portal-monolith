package ru.geekbrains.musicportal.entity.security;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class UserMembership {
    @Getter
    @Setter
    @Column(name = "is_approved")
    private boolean isApproved;

    @Getter
    @Setter
    @Column(name = "is_locked_out")
    private boolean isLockedOut;

    @Getter
    @Setter
    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;

    @Getter
    @Setter
    @Column(name = "last_login_date")
    @CreationTimestamp
    private LocalDateTime lastLoginDate;

    @Getter
    @Setter
    @Column(name = "last_lockout_date")
    @CreationTimestamp
    private LocalDateTime lastLockoutDate;

    @Getter
    @Setter
    @Column(name = "last_password_change_date")
    @CreationTimestamp
    private LocalDateTime lastPasswordChangeDate;

    @Getter
    @Setter
    @Column(name = "failed_password_attempt_count")
    private int failedPasswordAttemptCount;

    @Getter
    @Setter
    @Column(name = "failed_password_attempt_window_start")
    @CreationTimestamp
    private LocalDateTime failedPasswordAttemptWindowStart;

    @Getter
    @Setter
    @Column(name = "failed_password_answer_attempt_count")
    private int FailedPasswordAnswerAttemptCount;

    @Getter
    @Setter
    @Column(name = "failed_password_answer_attempt_window_start")
    @CreationTimestamp
    private LocalDateTime FailedPasswordAnswerAttemptWindowStart;

    @Getter
    @Setter
    @Column(name = "comment")
    private String comment;
}
