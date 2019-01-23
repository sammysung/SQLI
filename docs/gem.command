#!/bin/bash

cd "$(dirname "$0")"
gem install bundler --user-install
bundle install
bundle update
bundle exec jekyll serve
