// ==UserScript==
// @name           Script
// @namespace      http://www.greasespot.net/
// @include        *
// ==/UserScript==


location.href = "javascript:(" + function() {
  window.onbeforeunload = null;
  window.onunload = null;
} + ")()";
