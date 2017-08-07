(ns ^:figwheel-no-load nato.dev
  (:require
    [nato.core :as core]
    [devtools.core :as devtools]))


(enable-console-print!)

(devtools/install!)

(core/init!)
