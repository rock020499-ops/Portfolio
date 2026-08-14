package com.ownersgate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ownersgate.entity.SelfDisposalLog;

/**
 * 自力処分の記録のリポジトリ。
 * JpaRepository を継承することで、CRUD 操作が自動で使える。
 */
public interface SelfDisposalLogRepository extends JpaRepository<SelfDisposalLog, Long> {
}
