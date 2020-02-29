package com.std.boot.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
// JPA Entity 클래스들이 BseTimeEntity 를 상속할 경우 상속하는 필드들도 컬럼으로 인식하도록 한다.
@MappedSuperclass
// BaseTimeEntity 클래스에서 Auditing 기능을 포함시킴.
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    // Entity가 생성되어 저장될 떄 시간이 자동저장 된다.
    @CreatedDate
    private LocalDateTime createDate;

    // 조회된 Entity의 값을 변경할 때 시간이 자동 저장된다.
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
