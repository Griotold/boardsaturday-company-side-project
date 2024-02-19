package com.bizplus.boardsaturday.application.component.category;

import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import com.bizplus.boardsaturday.domain.repository.PostRepository;
import com.bizplus.boardsaturday.global.error.ErrorCode;
import com.bizplus.boardsaturday.global.error.ex.CategoryReferencedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
@Transactional
public class CategoryDeleter {
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;

    /**
     * @throws CategoryReferencedException 게시글이 참조하는 카테고리일 경우
     * */
    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("카테고리를 찾을 수 없습니다."));

        Long count = postRepository.getCountByCategory(category);
        if (count > 0) {
            throw new CategoryReferencedException(ErrorCode.CONFLICT, "게시글이 참조하는 카테고리입니다. 삭제할 수 없습니다.");
        }

        categoryRepository.delete(category);
    }
}
