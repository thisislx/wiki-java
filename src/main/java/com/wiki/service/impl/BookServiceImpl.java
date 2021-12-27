package com.wiki.service.impl;

import com.wiki.entity.Book;
import com.wiki.mapper.BookMapper;
import com.wiki.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xin.liu
 * @since 2021-12-27
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

}
