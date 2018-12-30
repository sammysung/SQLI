#!/bin/bash

cd "$(dirname "$0")"
gem install bundler
bundle install
bundle update
bundle exec jekyll serve
