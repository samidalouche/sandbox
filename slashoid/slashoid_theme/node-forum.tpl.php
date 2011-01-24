<div class="node ntype-forum<?php if ($sticky) { print " sticky"; } ?><?php if (!$status) { print " node-unpublished"; } ?>" id="node-<?php print $node->nid; ?>">
  <h2 class="title"><a href="<?php print $node_url ?>"><?php print $title; ?></a></h2>

  <?php if ($picture) print $picture; ?>

  <?php if ($submitted): ?>
    <div class="storydata">
      <?php print t('Posted !date by !name', array('!date' => format_date($node->created, 'custom', "F jS, Y"), '!name' => theme('username', $node))); ?>
    </div>
  <?php endif; ?>

  <div class="content">
    <?php print $content; ?>
  </div>

  <?php if ($links): ?>
    <div class="links">
      <?php print $links; ?>
    </div>
  <?php endif; ?>
</div>
