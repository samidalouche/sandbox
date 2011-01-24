<div class="comment <?php print $zebra; ?>">
  <?php $u=user_load(array('uid'=>$comment->uid)); ?>
  
  <h3><?php print $title; ?></h3>
  <?php if ($picture) print $picture; ?>

  <h4><?php print theme('username', $u); ?> <small><?php print format_interval(time() - $comment->timestamp, 4); ?> ago</small></h4>
    
  <div class="content">
    <?php print $content; ?>
  </div>
  
  <div class="links">
    <?php print $links; ?>
  </div>
</div>
