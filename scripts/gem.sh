#!/bin/bash

cd ../docs
gem install bundler
bundle install
bundle update
bundle exec jekyll serve
