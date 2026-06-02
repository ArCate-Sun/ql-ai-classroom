-- ========================================
-- OpenMAIC Database Schema v1
-- Initial Schema Creation
-- ========================================

-- ========================================
-- User Account Tables
-- ========================================

CREATE TABLE ac_account (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'User ID',
    email VARCHAR(255) UNIQUE NOT NULL COMMENT 'Email (login credential)',
    password_hash VARCHAR(255) NOT NULL COMMENT 'BCrypt password hash',
    nickname VARCHAR(100) COMMENT 'Display name',
    avatar_url VARCHAR(500) COMMENT 'Avatar URL',
    bio TEXT COMMENT 'User bio/introduction',

    status VARCHAR(32) DEFAULT 'active' NOT NULL COMMENT 'Status: active/inactive/banned',
    email_verified BOOLEAN DEFAULT FALSE COMMENT 'Email verification status',

    deleted BOOLEAN DEFAULT FALSE COMMENT 'Soft delete flag',
    created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT 'Creation time',
    updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT 'Update time',

    INDEX idx_email (email),
    INDEX idx_status (status),
    INDEX idx_created (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='User Account Table';

-- ========================================
-- Classroom Tables
-- ========================================

CREATE TABLE cr_classroom (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Classroom ID',
    classroom_id VARCHAR(64) UNIQUE NOT NULL COMMENT 'Classroom unique identifier (UUID)',

    title VARCHAR(255) COMMENT 'Classroom title',
    description TEXT COMMENT 'Classroom description',
    topic VARCHAR(255) COMMENT 'Main topic/subject',
    duration_minutes INT COMMENT 'Expected duration in minutes',

    status VARCHAR(32) DEFAULT 'draft' NOT NULL COMMENT 'Status: draft/published/archived',
    metadata JSON DEFAULT '{}' COMMENT 'Additional metadata (JSON)',

    owner_id BIGINT DEFAULT 0 NOT NULL COMMENT 'Creator ID (0=anonymous)',
    tenant_id BIGINT DEFAULT 0 NOT NULL COMMENT 'Tenant ID (reserved, default 0)',

    deleted BOOLEAN DEFAULT FALSE COMMENT 'Soft delete flag',
    created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT 'Creation time',
    updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT 'Update time',

    INDEX idx_classroom_id (classroom_id),
    INDEX idx_owner (owner_id),
    INDEX idx_tenant_owner (tenant_id, owner_id),
    INDEX idx_status (status),
    INDEX idx_created (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Classroom Table';

CREATE TABLE cr_stage (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Stage ID',
    classroom_id BIGINT NOT NULL COMMENT 'Associated classroom ID',

    config JSON DEFAULT '{}' COMMENT 'Stage configuration (JSON)',

    deleted BOOLEAN DEFAULT FALSE COMMENT 'Soft delete flag',
    created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT 'Creation time',
    updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT 'Update time',

    FOREIGN KEY (classroom_id) REFERENCES cr_classroom(id) ON DELETE CASCADE,
    INDEX idx_classroom (classroom_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Stage Configuration Table';

CREATE TABLE cr_scene (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Scene ID',
    scene_id VARCHAR(64) NOT NULL COMMENT 'Scene unique identifier',
    classroom_id BIGINT NOT NULL COMMENT 'Associated classroom ID',

    scene_type VARCHAR(32) NOT NULL COMMENT 'Scene type: slide/quiz/interactive/pbl',
    title VARCHAR(255) COMMENT 'Scene title',
    sort_order INT DEFAULT 0 NOT NULL COMMENT 'Display order',

    content MEDIUMTEXT COMMENT 'Scene content (JSON)',
    metadata JSON DEFAULT '{}' COMMENT 'Additional metadata',

    deleted BOOLEAN DEFAULT FALSE COMMENT 'Soft delete flag',
    created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT 'Creation time',
    updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT 'Update time',

    FOREIGN KEY (classroom_id) REFERENCES cr_classroom(id) ON DELETE CASCADE,
    INDEX idx_classroom_scene (classroom_id, sort_order),
    INDEX idx_scene_type (scene_type),
    INDEX idx_scene_id (scene_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Scene Table';

-- ========================================
-- Chat Session Tables
-- ========================================

CREATE TABLE ch_session (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Session ID',
    session_id VARCHAR(64) UNIQUE NOT NULL COMMENT 'Session unique identifier',
    classroom_id BIGINT COMMENT 'Associated classroom ID (nullable)',

    session_type VARCHAR(32) NOT NULL COMMENT 'Session type: qa/discussion/roundtable',
    agent_ids JSON DEFAULT '[]' COMMENT 'Participating agent IDs (JSON array)',

    owner_id BIGINT DEFAULT 0 NOT NULL COMMENT 'Creator ID (0=anonymous)',
    tenant_id BIGINT DEFAULT 0 NOT NULL COMMENT 'Tenant ID (reserved, default 0)',

    deleted BOOLEAN DEFAULT FALSE COMMENT 'Soft delete flag',
    created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT 'Creation time',
    updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT 'Update time',

    INDEX idx_session_id (session_id),
    INDEX idx_classroom (classroom_id),
    INDEX idx_owner (owner_id),
    INDEX idx_tenant_owner (tenant_id, owner_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Chat Session Table';

CREATE TABLE ch_message (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Message ID',
    session_id BIGINT NOT NULL COMMENT 'Session ID',

    role VARCHAR(32) NOT NULL COMMENT 'Message role: user/assistant/system',
    agent_id VARCHAR(64) COMMENT 'Agent identifier (for assistant role)',
    content MEDIUMTEXT NOT NULL COMMENT 'Message content',
    metadata JSON DEFAULT '{}' COMMENT 'Additional metadata',

    deleted BOOLEAN DEFAULT FALSE COMMENT 'Soft delete flag',
    created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT 'Creation time',

    FOREIGN KEY (session_id) REFERENCES ch_session(id) ON DELETE CASCADE,
    INDEX idx_session_created (session_id, created_at),
    INDEX idx_role (role)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Chat Message Table';

-- ========================================
-- Generation Job Tables
-- ========================================

CREATE TABLE gn_generation_job (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Job ID',
    job_id VARCHAR(64) UNIQUE NOT NULL COMMENT 'Job unique identifier',

    job_type VARCHAR(32) NOT NULL COMMENT 'Job type: classroom/outline/scenes',
    status VARCHAR(32) DEFAULT 'pending' NOT NULL COMMENT 'Status: pending/running/completed/failed',
    progress INT DEFAULT 0 COMMENT 'Progress percentage (0-100)',

    request_data MEDIUMTEXT COMMENT 'Request parameters (JSON)',
    result_data MEDIUMTEXT COMMENT 'Generation result (JSON)',
    error_message TEXT COMMENT 'Error message if failed',

    owner_id BIGINT DEFAULT 0 NOT NULL COMMENT 'Creator ID (0=anonymous)',
    tenant_id BIGINT DEFAULT 0 NOT NULL COMMENT 'Tenant ID (reserved, default 0)',

    created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT 'Creation time',
    updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT 'Update time',
    completed_at DATETIME(6) COMMENT 'Completion time',

    INDEX idx_job_id (job_id),
    INDEX idx_status (status),
    INDEX idx_owner (owner_id),
    INDEX idx_tenant_owner (tenant_id, owner_id),
    INDEX idx_created (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Generation Job Table';

-- ========================================
-- Storage Tables
-- ========================================

CREATE TABLE st_storage_file (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'File ID',
    file_id VARCHAR(64) UNIQUE NOT NULL COMMENT 'File unique identifier',

    original_name VARCHAR(255) NOT NULL COMMENT 'Original file name',
    stored_name VARCHAR(255) NOT NULL COMMENT 'Stored file name',
    file_path VARCHAR(1000) NOT NULL COMMENT 'File storage path',
    file_size BIGINT NOT NULL COMMENT 'File size in bytes',
    mime_type VARCHAR(100) COMMENT 'MIME type',

    storage_type VARCHAR(32) DEFAULT 'local' NOT NULL COMMENT 'Storage type: local/oss',

    owner_id BIGINT DEFAULT 0 NOT NULL COMMENT 'Uploader ID (0=anonymous)',
    tenant_id BIGINT DEFAULT 0 NOT NULL COMMENT 'Tenant ID (reserved, default 0)',

    deleted BOOLEAN DEFAULT FALSE COMMENT 'Soft delete flag',
    created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT 'Upload time',
    updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT 'Update time',

    INDEX idx_file_id (file_id),
    INDEX idx_owner (owner_id),
    INDEX idx_tenant_owner (tenant_id, owner_id),
    INDEX idx_created (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Storage File Table';

-- ========================================
-- Initial Data
-- ========================================

-- No initial data for v1
