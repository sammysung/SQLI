#!/bin/bash

cd "$(dirname "$0")"
cd ..
cd ../docs
gem install bundler --user-install
bundle install
bundle update
bundle exec jekyll serve
cd ../scripts/osx