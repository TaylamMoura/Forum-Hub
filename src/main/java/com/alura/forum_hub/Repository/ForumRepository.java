package com.alura.forum_hub.Repository;

import com.alura.forum_hub.Forum.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<Forum, Long> {
}
